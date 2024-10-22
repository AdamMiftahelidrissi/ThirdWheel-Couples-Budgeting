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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.example.thirdwheel.components.LabelValueColumn
import org.example.thirdwheel.components.SectionWithListCard
import org.example.thirdwheel.components.AddExpenseOrIncomeBottomSheetContent
import org.example.thirdwheel.components.AddTransactionBottomSheetContent
import org.example.thirdwheel.stateholders.AppStateHolder

// Enum to track which bottom sheet is active
enum class BottomSheetType {
    ADD_TRANSACTION,
    ADD_EXPENSE_OR_INCOME
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(appStateHolder: AppStateHolder) {
    // Bottom and top insets for padding
    val bottomInset = with(LocalDensity.current) { WindowInsets.safeDrawing.getBottom(this).toDp() }
    val topInset = with(LocalDensity.current) { WindowInsets.safeDrawing.getTop(this).toDp() }

    // State variables for tracking income, expenses, and balance from the AppStateHolder
    val income by remember { derivedStateOf { appStateHolder.totalIncome } }
    val expenses by remember { derivedStateOf { appStateHolder.totalExpenses } }
    val balance by remember { derivedStateOf { appStateHolder.balance } }

    // State for selected category and category fields
    var selectedCategory by remember { mutableStateOf("Expense") }
    var categoryName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("0.00") }
    var budgetAmount by remember { mutableStateOf("") }
    var showErrorMessage by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") } // New error message state

    // State for adding a transaction
    var transactionAmount by remember { mutableStateOf("") }

    // State for managing bottom sheet content and active sheet type
    var activeBottomSheet by remember { mutableStateOf<BottomSheetType?>(null) }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    // Monitor the bottom sheet state and reset activeBottomSheet to null if collapsed
    LaunchedEffect(scaffoldState.bottomSheetState.isCollapsed) {
        if (scaffoldState.bottomSheetState.isCollapsed) {
            activeBottomSheet = null
        }
    }

    // Expand or collapse the bottom sheet based on activeBottomSheet changes
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
            errorMessage = ""
            showErrorMessage = false
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            when (activeBottomSheet) {
                BottomSheetType.ADD_EXPENSE_OR_INCOME -> {
                    AddExpenseOrIncomeBottomSheetContent(
                        selectedCategory = selectedCategory,
                        onCategoryChange = { selectedCategory = it },
                        scaffoldState = scaffoldState,
                        categoryName = categoryName,
                        onCategoryNameChange = { categoryName = it },
                        budgetAmount = budgetAmount,
                        onBudgetAmountChange = { budgetAmount = it },
                        showErrorMessage = showErrorMessage,
                        onConfirm = {
                            if (categoryName.isEmpty()) {
                                showErrorMessage = true
                                errorMessage = "Category name cannot be empty."
                            } else {
                                if (selectedCategory == "Expense") {
                                    val success = appStateHolder.addExpenseCategory(
                                        categoryName, budgetAmount.toDoubleOrNull() ?: 0.0
                                    )
                                    if (!success) {
                                        showErrorMessage = true
                                        errorMessage = "Category with this name exists."
                                    } else {
                                        activeBottomSheet = null
                                    }
                                } else {
                                    val success = appStateHolder.addIncomeCategory(
                                        categoryName, amount.toDoubleOrNull() ?: 0.0
                                    )
                                    if (!success) {
                                        showErrorMessage = true
                                        errorMessage = "Category with this name exists."
                                    } else {
                                        activeBottomSheet = null
                                    }
                                }
                            }
                        }
                    )
                }
                BottomSheetType.ADD_TRANSACTION -> {
                    AddTransactionBottomSheetContent(
                        scaffoldState = scaffoldState,
                        transactionAmount = transactionAmount,
                        onTransactionAmountChange = { transactionAmount = it },
                        showErrorMessage = showErrorMessage,
                        errorMessage = errorMessage,
                        onConfirm = {
                            if (transactionAmount.isEmpty()) {
                                showErrorMessage = true
                                errorMessage = "Transaction amount cannot be empty."
                            } else {
                                showErrorMessage = false
                                appStateHolder.addTransaction(categoryName, transactionAmount.toDouble())
                                activeBottomSheet = null
                            }
                        }
                    )
                }
                null -> {
                    Spacer(modifier = Modifier.height(0.dp))
                }
            }
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
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
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Overview",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        LabelValueColumn(label = "Income", value = income)
                        LabelValueColumn(label = "Expenses", value = expenses)
                        LabelValueColumn(label = "Balance", value = balance)
                    }
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item {
                    SectionWithListCard(
                        title = "Income",
                        items = appStateHolder.incomeList,
                        placeholder = "No incomes added yet",
                        onItemClick = {
                            categoryName = it
                            transactionAmount = ""
                            showErrorMessage = false
                            activeBottomSheet = BottomSheetType.ADD_TRANSACTION
                        }
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    SectionWithListCard(
                        title = "Expenses",
                        items = appStateHolder.expenseList,
                        placeholder = "No expenses added yet",
                        onItemClick = {
                            categoryName = it
                            transactionAmount = ""
                            showErrorMessage = false
                            activeBottomSheet = BottomSheetType.ADD_TRANSACTION
                        }
                    )
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
            }

            FloatingActionButton(
                onClick = {
                    selectedCategory = "Expense"
                    categoryName = ""
                    amount = "0.00"
                    budgetAmount = ""
                    showErrorMessage = false
                    errorMessage = ""
                    activeBottomSheet = BottomSheetType.ADD_EXPENSE_OR_INCOME
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

