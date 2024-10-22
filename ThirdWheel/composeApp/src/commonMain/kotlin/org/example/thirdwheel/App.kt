package org.example.thirdwheel

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.example.thirdwheel.screens.LoginScreen
import org.example.thirdwheel.screens.ForgotPasswordScreen
import org.example.thirdwheel.screens.MainScreen
import org.example.thirdwheel.screens.SignUpScreen

@Composable
fun App() {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf("login") }

        when (currentScreen) {
            "login" -> LoginScreen(
                onLoginSuccess = { email, password ->
                    // Handle login logic here, for example:
                    // if (isValidLogin(email, password)) {
                    currentScreen = "mainscreen" },
                onForgotPasswordClick = { currentScreen = "forgotpassword" },
                onSignUpClick = { currentScreen = "signup" }
            )
            "forgotpassword" -> ForgotPasswordScreen(
                onPasswordReset = { email ->
                    // Handle password reset logic here, for example:
                    // show a success message, or trigger an API call
                    currentScreen = "login"  // Navigate back to login after reset
                },
                onBackToLoginClick = { currentScreen = "login" }  // Navigate back to login screen
            )
            "signup" -> SignUpScreen(
                onSignUpSuccess = { currentScreen = "mainscreen" },
                onBackToLoginClick = { currentScreen = "login" },
            )
            "mainscreen" -> MainScreen()
        }
    }
}