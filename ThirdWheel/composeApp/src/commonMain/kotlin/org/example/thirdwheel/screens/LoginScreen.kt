package org.example.thirdwheel.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.thirdwheel.components.CustomButton
import org.example.thirdwheel.components.CustomTextButton
import org.example.thirdwheel.components.CustomTextField

@Composable
fun LoginScreen(
    onLoginSuccess: (String, String) -> Unit,  // Pass email and password on login
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }       // State for email input
    var password by remember { mutableStateOf("") }    // State for password input
    var showError by remember { mutableStateOf(false) } // To handle login error visibility
    var showPassword by remember { mutableStateOf(false) } // To handle password visibility

    Column(
        modifier = Modifier
            .fillMaxSize()  // Fill the screen size for better alignment
            .padding(horizontal = 24.dp, vertical = 32.dp), // Added more padding for a clean layout
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Center items vertically
    ) {
        // Login Title
        Text(
            text = "Login Here",
            fontWeight = FontWeight.Bold, // Bold for emphasis
            fontSize = 28.sp, // Slightly smaller for a cleaner look
            color = Color(0xFF4BAFAD) // Teal color for the title
        )

        Spacer(modifier = Modifier.height(12.dp)) // Reduced space between title and subtitle

        // Small Subtitle
        Text(
            text = "The Budgeting Tool for Two, Powered by Three",
            style = MaterialTheme.typography.body2.copy(
                fontSize = 14.sp, // Slightly smaller font size for a modern, clean look
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(24.dp)) // Space between subtitle and email field

        // Email Input Field
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email Address",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(16.dp)) // Spacing between email and password fields

        // Password Input Field
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPasswordField = true,
            showPassword = showPassword,
            onShowPasswordToggle = { showPassword = !showPassword }
        )

        // Error message and Forgot Password Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), // Space between password field and row
            horizontalArrangement = Arrangement.SpaceBetween, // Spread out items
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Error message if email or password is invalid
            if (showError) {
                Text(
                    text = "Invalid email or password",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.body2
                )
            } else {
                Spacer(modifier = Modifier.weight(1f)) // Empty space to maintain layout consistency
            }

            // Forgot Password Button
            CustomTextButton(
                text = "Forgot Password?",
                onClick = onForgotPasswordClick,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(10.dp)) // Space before login button

        // Login Button
        CustomButton(
            text = "Log in",
            modifier = Modifier.fillMaxWidth(), // Button fills the available width
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    onLoginSuccess(email, password)
                } else {
                    showError = true // Show error if fields are empty
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Space between login button and last row

        // Row with "Don't have an account?" text and Sign Up button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center, // Center items in the row
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?",
                style = MaterialTheme.typography.body2.copy(
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between text and sign-up button
            CustomTextButton(
                text = "Sign up",
                onClick = onSignUpClick
            )
        }
    }
}
