package org.example.thirdwheel.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.thirdwheel.formatCurrency
import org.example.thirdwheel.stateholders.AppStateHolder

@Composable
fun BudgetScreen(appStateHolder: AppStateHolder) {
    // Bottom and top insets for padding
    val bottomInset = with(LocalDensity.current) { WindowInsets.safeDrawing.getBottom(this).toDp() }
    val topInset = with(LocalDensity.current) { WindowInsets.safeDrawing.getTop(this).toDp() }

    // Main content of the screen
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Budget",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }

            if (appStateHolder.expenseBudgets.isEmpty()){
                item {
                    Text(
                        text = "No budgets added yet",
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        )
                }
            }

            // Dynamically display each budget entry from the AppStateHolder
            items(appStateHolder.expenseBudgets) { budget ->
                BudgetItem(
                    budget,
                    onClick = {

                    }
                )
            }
        }
    }
}

@Composable
fun BudgetItem(
    budget: AppStateHolder.BudgetEntry,
    onClick: (AppStateHolder.BudgetEntry) -> Unit // Handle click events
) {
    // Calculate the progress based on spent amount and budget amount (between 0.0 to 1.0)
    val progress = (budget.spentAmount / budget.budgetAmount).toFloat().coerceIn(0f, 1f)

    // Determine the color based on the progress percentage
    val progressColor = when {
        progress < 0.6f -> Color(0xFF4BAFAD) // Teal for progress between 0-59%
        progress < 0.8f -> Color.Yellow // Yellow for progress between 60-79%
        else -> Color.Red // Red for progress 80% and above
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(budget) }, // Card becomes clickable
        shape = RoundedCornerShape(8.dp), // Rounded corners for a modern look
        elevation = 4.dp // Elevation for shadow effect
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding inside the card
        ) {
            // Display category name
            Text(
                text = budget.categoryName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp)) // Space between title and row

            // Row to display spent amount and budget amount
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display spent amount
                Text(
                    text = "Spent: ${formatCurrency(budget.spentAmount)}",
                    fontSize = 16.sp,
                    color = Color.Black
                )

                // Display budget amount
                Text(
                    text = "Max: ${formatCurrency(budget.budgetAmount)}",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Space before progress bar

            // Linear progress indicator with dynamic color
            LinearProgressIndicator(
                progress = progress, // Progress value between 0.0 and 1.0
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp), // Thin progress line
                color = progressColor, // Dynamic color based on progress
                backgroundColor = Color.LightGray // Background color of the bar
            )
        }
    }
}
