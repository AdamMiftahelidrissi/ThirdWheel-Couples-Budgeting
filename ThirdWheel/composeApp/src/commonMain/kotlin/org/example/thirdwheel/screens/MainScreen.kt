package org.example.thirdwheel.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.thirdwheel.stateholders.AppStateHolder

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("home") } // Track the current screen

    // Create the shared AppStateHolder instance
    val appStateHolder = remember { AppStateHolder() }

    Scaffold(
        bottomBar = {
            BottomNavigation(currentScreen) { selectedScreen ->
                currentScreen = selectedScreen // Update current screen when a tab is selected
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // The content that changes based on the selected tab
            when (currentScreen) {
                "home" -> HomeScreen(appStateHolder)
                "transactions" -> TransactionsScreen(appStateHolder)
                "balances" -> BalancesScreen(appStateHolder)
                "budget" -> BudgetScreen(appStateHolder)
            }
        }
    }
}

@Composable
fun BottomNavigation(currentScreen: String, onTabSelected: (String) -> Unit) {
    val bottomInset = WindowInsets.safeDrawing.getBottom(LocalDensity.current)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color.White,
            contentColor = Color.Black
        ) {
            BottomNavigationItem(
                selected = currentScreen == "home",
                onClick = { onTabSelected("home") },
                icon = {
                    Icon(
                        imageVector = if (currentScreen == "home") Icons.Filled.Home else Icons.Outlined.Home,
                        contentDescription = "Home"
                    )
                },
                label = { Text("Home", fontSize = 11.sp) },
                selectedContentColor = Color(0xFF4BAFAD),
                unselectedContentColor = Color.Black
            )
            BottomNavigationItem(
                selected = currentScreen == "transactions",
                onClick = { onTabSelected("transactions") },
                icon = {
                    Icon(
                        imageVector = if (currentScreen == "transactions") Icons.Filled.List else Icons.Outlined.List,
                        contentDescription = "Transactions"
                    )
                },
                label = { Text("Transactions", fontSize = 11.sp) },
                selectedContentColor = Color(0xFF4BAFAD),
                unselectedContentColor = Color.Black
            )
            BottomNavigationItem(
                selected = currentScreen == "balances",
                onClick = { onTabSelected("balances") },
                icon = {
                    Icon(
                        imageVector = if (currentScreen == "balances") Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "Balances"
                    )
                },
                label = { Text("Balances", fontSize = 11.sp) },
                selectedContentColor = Color(0xFF4BAFAD),
                unselectedContentColor = Color.Black
            )
            BottomNavigationItem(
                selected = currentScreen == "budget",
                onClick = { onTabSelected("budget") },
                icon = {
                    Icon(
                        imageVector = if (currentScreen == "budget") Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
                        contentDescription = "Budget"
                    )
                },
                label = { Text("Budget", fontSize = 11.sp) },
                selectedContentColor = Color(0xFF4BAFAD),
                unselectedContentColor = Color.Black
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalDensity.current) { bottomInset.toDp() })
                .background(Color.White)
        )
    }
}
