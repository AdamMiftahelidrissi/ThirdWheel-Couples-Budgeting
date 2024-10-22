package org.example.thirdwheel.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import org.example.thirdwheel.stateholders.AppStateHolder

@Composable
fun TransactionsScreen(appStateHolder: AppStateHolder) {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Transactions",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Section for transaction list
            item {
                SectionWithListCard(
                    title = "Transactions",
                    items = appStateHolder.transactionsList, // Use shared state
                    placeholder = "No entries added yet",
                    onItemClick = {

                    }
                )
            }
        }
    }
}