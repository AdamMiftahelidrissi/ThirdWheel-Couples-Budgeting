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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddExpenseOrIncomeBottomSheetContent(
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    scaffoldState: BottomSheetScaffoldState,
    categoryName: String,
    onCategoryNameChange: (String) -> Unit,
    budgetAmount: String,
    onBudgetAmountChange: (String) -> Unit,
    showErrorMessage: Boolean,
    onConfirm: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    // Content inside the bottom sheet
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    // Close the bottom sheet when the close button is clicked
                    coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                },
                modifier = Modifier.align(Alignment.CenterStart) // Center the close button to the start
            ) {
                // Close button to collapse the bottom sheet
                Icon(Icons.Default.Close, contentDescription = "Close")
            }

            Text(
                text = "New Category",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center) // Center the text
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally) // Center the chips and add space between them
        ) {
            listOf("Expense", "Income").forEach { category ->
                Chip(
                    onClick = { onCategoryChange(category) },
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

        Spacer(modifier = Modifier.height(8.dp)) // Space between chips and category name field

        // Row for category name text and text field
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Name",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(80.dp) // Set the same width for all labels
                    .padding(end = 8.dp)
            )

            // Category name text field
            CustomTextField(
                value = categoryName,
                onValueChange = onCategoryNameChange,
                placeholder = "",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f) // Ensure the text field takes up the remaining space
            )
        }

        if (selectedCategory == "Expense") {

            Spacer(modifier = Modifier.height(8.dp)) // Space between category name field and budget field

            // Row for budget amount text and text field
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Budget",
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(80.dp) // Same width for alignment
                        .padding(end = 8.dp)
                )

                // Budget amount text field
                CustomTextField(
                    value = budgetAmount,
                    onValueChange = onBudgetAmountChange,
                    placeholder = "0.00",
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f) // Ensure the text field takes up the remaining space
                )
            }
        }

        if (showErrorMessage) {
            Text(
                text = "Please enter a name for the category",
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Space between budget field and button

        CustomButton(
            text = "Add",
            onClick = onConfirm
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddTransactionBottomSheetContent(
    scaffoldState: BottomSheetScaffoldState,
    transactionAmount: String,
    onTransactionAmountChange: (String) -> Unit,
    showErrorMessage: Boolean,
    errorMessage: String?, // Error message string
    onConfirm: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween // Space content evenly
    ) {
        // Header section with title and close button
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }

            Text(
                text = "New Transaction",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Transaction Amount Field
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Amount",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(80.dp)
                    .padding(end = 8.dp)
            )
            CustomTextField(
                value = transactionAmount,
                onValueChange = onTransactionAmountChange,
                placeholder = "0.00",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Account Selection Placeholder Row
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Account",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(80.dp)
                    .padding(end = 8.dp)
            )
            // Account selection UI can go here
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Date Selection Placeholder Row
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Date",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(80.dp)
                    .padding(end = 8.dp)
            )
            // Date selection UI can go here
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Repeating Option Placeholder Row
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Repeating",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(80.dp)
                    .padding(end = 8.dp)
            )
            // Repeating option UI can go here
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Error message displayed at the bottom, if present
        if (showErrorMessage && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )
        }

        // Add button aligned to the bottom
        CustomButton(
            text = "Add",
            onClick = onConfirm,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}
