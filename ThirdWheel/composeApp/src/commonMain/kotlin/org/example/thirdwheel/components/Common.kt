package org.example.thirdwheel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.thirdwheel.formatCurrency

@Composable
fun LabelValueColumn(label: String, value: Double) {
    // Column displaying the label and the formatted value
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontWeight = FontWeight.Normal)
        Text(text = formatCurrency(value), fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
    }
}

@Composable
fun SectionWithListCard(
    title: String,
    items: List<Pair<String, Double>>,
    placeholder: String,
    onItemClick: (String) -> Unit
) {
    // Padding values for title and items
    val titlePaddingStart = 16.dp
    val itemPaddingStart = 0.dp

    // Section with a title and a list of items
    Column(
        modifier = Modifier
            .padding(start = titlePaddingStart)
            .fillMaxWidth()
    ) {
        // Title of the section
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (items.isEmpty()) {
            // Display placeholder if the list is empty
            Text(
                text = placeholder,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = itemPaddingStart, vertical = 4.dp)
            )
        } else {
            items.forEach { (category, amount) ->
                // Each item as a Card with visual feedback
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = itemPaddingStart, vertical = 6.dp)
                        .clickable { onItemClick(category) }, // Handle click
                    shape = RoundedCornerShape(8.dp),
                    elevation = 4.dp, // Elevation for shadow effect
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp), // Padding inside the card
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = category, // Category name
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = formatCurrency(amount), // Formatted amount
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AccountSelectionDropdown(
    label: String,
    accounts: List<String>,
    selectedAccount: String,
    onAccountSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        // Label for the dropdown
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Dropdown Box with Teal Border and White Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF4BAFAD), // Teal border color
                    shape = RoundedCornerShape(8.dp)
                )
                .background(Color.White, shape = RoundedCornerShape(8.dp)) // White background
                .clickable { expanded = true } // Open dropdown on click
                .padding(horizontal = 8.dp), // Inner padding
            contentAlignment = Alignment.CenterStart
        ) {
            // Selected Account Text
            Text(
                text = selectedAccount.ifEmpty { "Select an Account" },
                color = Color.Black, // Black text color
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Dropdown Menu with Account Options
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            accounts.forEach { account ->
                DropdownMenuItem(
                    onClick = {
                        onAccountSelected(account)
                        expanded = false // Close dropdown after selection
                    }
                ) {
                    Text(
                        text = account,
                        color = Color.Black, // Black text inside dropdown
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
