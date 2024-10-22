package org.example.thirdwheel.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.example.thirdwheel.components.AddAccountBottomSheetContent
import org.example.thirdwheel.components.InteractWithAccountBottomSheetContent
import org.example.thirdwheel.components.LabelValueColumn
import org.example.thirdwheel.components.SectionWithListCard
import org.example.thirdwheel.stateholders.AppStateHolder

// Enum to track which bottom sheet is active
enum class BalancesScreenBottomSheetType {
    ADD_ACCOUNT,
    INTERACT_WITH_ACCOUNT
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BalancesScreen(appStateHolder: AppStateHolder) {
    val bottomInset = with(LocalDensity.current) { WindowInsets.safeDrawing.getBottom(this).toDp() }
    val topInset = with(LocalDensity.current) { WindowInsets.safeDrawing.getTop(this).toDp() }

    val assets by remember { derivedStateOf { appStateHolder.totalAssets } }
    val liabilities by remember { derivedStateOf { appStateHolder.totalLiabilities } }
    val netWorth by remember { derivedStateOf { appStateHolder.totalNetWorth } }

    var selectedAccountType by remember { mutableStateOf("Assets") }
    var accountName by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("0.00") }
    var amount by remember { mutableStateOf("0.00") }
    var toAccount by remember { mutableStateOf("") }
    var fromAccount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Deposit") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showErrorMessage by remember { mutableStateOf(false) }

    var activeBottomSheet by remember { mutableStateOf<BalancesScreenBottomSheetType?>(null) }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(scaffoldState.bottomSheetState.isCollapsed) {
        if (scaffoldState.bottomSheetState.isCollapsed) {
            activeBottomSheet = null
        }
    }

    LaunchedEffect(activeBottomSheet) {
        if (activeBottomSheet != null) {
            scaffoldState.bottomSheetState.expand()
        } else {
            scaffoldState.bottomSheetState.collapse()
        }
    }

    LaunchedEffect(scaffoldState.bottomSheetState.isCollapsed) {
        if (scaffoldState.bottomSheetState.isCollapsed) {
            // Reset error state when the bottom sheet collapses
            errorMessage = null
            showErrorMessage = false
            fromAccount = "" // Reset accounts to avoid stale selections
            toAccount = ""
            amount = ""
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            when (activeBottomSheet) {
                BalancesScreenBottomSheetType.ADD_ACCOUNT -> {
                    AddAccountBottomSheetContent(
                        scaffoldState = scaffoldState,
                        onAccountTypeChange = { selectedAccountType = it },
                        selectedAccountType = selectedAccountType,
                        accountName = accountName,
                        onAccountNameChange = { accountName = it },
                        balance = balance,
                        onBalanceChange = { balance = it },
                        errorMessage = errorMessage,
                        onConfirm = {
                            val parsedBalance = balance.toDoubleOrNull() ?: 0.0
                            when {
                                accountName.isEmpty() -> errorMessage = "Please enter a name"
                                selectedAccountType == "Assets" && !appStateHolder.addAssets(accountName, parsedBalance) ->
                                    errorMessage = "Duplicate account name"
                                selectedAccountType == "Liabilities" && !appStateHolder.addLiabilities(accountName, parsedBalance) ->
                                    errorMessage = "Duplicate account name"
                                selectedAccountType == "Savings" && !appStateHolder.addSavings(accountName, parsedBalance) ->
                                    errorMessage = "Duplicate savings name"
                                else -> {
                                    errorMessage = null
                                    coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                                }
                            }
                        }
                    )
                }
                BalancesScreenBottomSheetType.INTERACT_WITH_ACCOUNT -> {
                    InteractWithAccountBottomSheetContent(
                        scaffoldState = scaffoldState,
                        accounts = appStateHolder.allAccountsList,
                        accountName = accountName, // Show selected account at the top
                        fromAccount = fromAccount,
                        onFromAccountChange = { fromAccount = it },
                        toAccount = toAccount,
                        onToAccountChange = { toAccount = it },
                        selectedCategory = selectedCategory,
                        onCategoryChange = { selectedCategory = it },
                        amount = amount,
                        onAmountChange = { amount = it },
                        errorMessage = errorMessage,
                        showErrorMessage = showErrorMessage,
                        setErrorMessage = { error ->
                            errorMessage = error
                            showErrorMessage = error != null
                        },
                        onConfirm = {
                            val parsedAmount = amount.toDoubleOrNull() ?: 0.0

                            // Clear previous error before performing the operation
                            errorMessage = null
                            showErrorMessage = false

                            when (selectedCategory) {
                                "Deposit" -> {
                                    if (appStateHolder.deposit(fromAccount, parsedAmount)) {
                                        coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                                    } else {
                                        errorMessage = "Invalid account"
                                        showErrorMessage = true
                                    }
                                }
                                "Withdraw" -> {
                                    if (appStateHolder.withdraw(fromAccount, parsedAmount)) {
                                        coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                                    } else {
                                        errorMessage = "Insufficient balance"
                                        showErrorMessage = true
                                    }
                                }
                                "Transfer" -> {
                                    if (fromAccount == toAccount) {
                                        errorMessage = "Cannot transfer to the same account"
                                        showErrorMessage = true
                                    } else if (appStateHolder.transfer(fromAccount, toAccount, parsedAmount)) {
                                        coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                                    } else {
                                        errorMessage = "Transfer failed"
                                        showErrorMessage = true
                                    }
                                }
                            }
                        }
                    )
                }
                null -> Spacer(modifier = Modifier.height(0.dp))
            }
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomInset, top = topInset)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    Text(
                        text = "Balances",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        LabelValueColumn("Assets", assets)
                        LabelValueColumn("Net Worth", netWorth)
                        LabelValueColumn("Liabilities", liabilities)
                    }
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                listOf(
                    "Assets" to appStateHolder.assetsList,
                    "Liabilities" to appStateHolder.liabilitiesList,
                    "Savings" to appStateHolder.savingsList
                ).forEach { (title, list) ->
                    item {
                        SectionWithListCard(
                            title = title,
                            items = list, // Use the correct pair list
                            placeholder = "No entries added yet",
                            onItemClick = {
                                accountName = it
                                fromAccount = it
                                activeBottomSheet = BalancesScreenBottomSheetType.INTERACT_WITH_ACCOUNT
                            }
                        )
                    }
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }
            FloatingActionButton(
                onClick = {
                    selectedAccountType = "Assets"
                    accountName = ""
                    balance = ""
                    activeBottomSheet = BalancesScreenBottomSheetType.ADD_ACCOUNT
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                backgroundColor = Color(0xFF4BAFAD)
            ) {
                Text("+", color = Color.White, fontSize = 24.sp)
            }
        }
    }
}
