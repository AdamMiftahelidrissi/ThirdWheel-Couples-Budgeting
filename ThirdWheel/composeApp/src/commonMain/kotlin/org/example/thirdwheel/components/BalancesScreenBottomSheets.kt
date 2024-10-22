package org.example.thirdwheel.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddAccountBottomSheetContent(
    scaffoldState: BottomSheetScaffoldState,
    onAccountTypeChange: (String) -> Unit,
    selectedAccountType: String,
    accountName: String,
    onAccountNameChange: (String) -> Unit,
    balance: String,
    onBalanceChange: (String) -> Unit,
    errorMessage: String?,
    onConfirm: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { coroutineScope.launch { scaffoldState.bottomSheetState.collapse() } },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }

            Text(
                text = "New Account",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            listOf("Assets", "Liabilities", "Savings").forEach { accountType ->
                Chip(
                    onClick = { onAccountTypeChange(accountType) },
                    colors = ChipDefaults.chipColors(
                        backgroundColor = if (selectedAccountType == accountType) Color(0xFF4BAFAD) else Color.LightGray
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = accountType,
                        color = if (selectedAccountType == accountType) Color.White else Color.Black
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Name", fontSize = 16.sp, modifier = Modifier.width(80.dp).padding(end = 8.dp))
                CustomTextField(
                    value = accountName,
                    onValueChange = onAccountNameChange,
                    placeholder = "",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Balance", fontSize = 16.sp, modifier = Modifier.width(80.dp).padding(end = 8.dp))
                CustomTextField(
                    value = balance,
                    onValueChange = onBalanceChange,
                    placeholder = "0.00",
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(vertical = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(text = "Add", onClick = onConfirm)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InteractWithAccountBottomSheetContent(
    scaffoldState: BottomSheetScaffoldState,
    accountName: String,
    accounts: List<String>, // List of available accounts
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    fromAccount: String,
    onFromAccountChange: (String) -> Unit,
    toAccount: String,
    onToAccountChange: (String) -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit,
    errorMessage: String?, // State-driven error message
    showErrorMessage: Boolean,
    setErrorMessage: (String?) -> Unit, // Function to clear/set the error
    onConfirm: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header with Close Button
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { coroutineScope.launch { scaffoldState.bottomSheetState.collapse() } },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
            Text(
                text = accountName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        // Transaction Type Selector (Deposit, Withdraw, Transfer)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Deposit", "Withdraw", "Transfer").forEach { category ->
                Chip(
                    onClick = {
                        onCategoryChange(category)
                        setErrorMessage(null) // Clear error on chip change
                    },
                    colors = ChipDefaults.chipColors(
                        backgroundColor = if (selectedCategory == category) Color(0xFF4BAFAD) else Color.LightGray
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = category,
                        color = if (selectedCategory == category) Color.White else Color.Black
                    )
                }
            }
        }

        // Amount Input Field
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Amount",
                fontSize = 16.sp,
                modifier = Modifier
                    .width(80.dp)
                    .padding(end = 8.dp)
            )
            CustomTextField(
                value = amount,
                onValueChange = onAmountChange,
                placeholder = "0.00",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                modifier = Modifier.weight(1f)
            )
        }

        // Transfer Account Selection (Only if "Transfer" is selected)
        if (selectedCategory == "Transfer") {
            AccountSelectionDropdown(
                label = "From Account",
                accounts = accounts,
                selectedAccount = fromAccount,
                onAccountSelected = onFromAccountChange
            )

            Spacer(modifier = Modifier.height(8.dp))

            AccountSelectionDropdown(
                label = "To Account",
                accounts = accounts,
                selectedAccount = toAccount,
                onAccountSelected = onToAccountChange
            )
        }

        // Error Message Display
        if (showErrorMessage && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Button
        CustomButton(
            text = "Confirm",
            onClick = onConfirm,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
