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
fun ForgotPasswordScreen(
    onPasswordReset: (String) -> Unit,  // Callback to handle password reset with the entered email
    onBackToLoginClick: () -> Unit      // Option to navigate back to the login screen
) {
    var email by remember { mutableStateOf("") }  // State to store the user's email
    var showError by remember { mutableStateOf(false) }  // State to show error messages
    var errorMessage by remember { mutableStateOf("") }  // State to store the error message

    // Simple regex pattern for email validation (cross-platform)
    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    Column(
        modifier = Modifier
            .fillMaxSize()  // Fill the screen size for better alignment
            .padding(24.dp),  // Increased padding for a more spacious layout
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center  // Center everything vertically
    ) {
        // Screen Title
        Text(
            text = "Forgot Password",
            fontWeight = FontWeight.Bold,  // Bold for emphasis
            fontSize = 30.sp,
            color = Color(0xFF4BAFAD) // Slightly darker teal
        )

        Spacer(modifier = Modifier.height(24.dp)) // Space between title and email input

        // Email Input Field
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email Address",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(16.dp)) // Space between email input and error message

        // Show error message if validation fails
        if (showError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Submit Button
        CustomButton(
            text = "Submit",
            modifier = Modifier.fillMaxWidth(),  // Full width button for better UI
            onClick = {
                // Validate email format using regex
                if (!emailPattern.matches(email)) {
                    showError = true
                    errorMessage = "Invalid email address"  // Show error message for invalid email
                } else {
                    showError = false  // Reset error if email is valid
                    onPasswordReset(email)  // Trigger password reset when fields are valid
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Space between submit button and back button

        // Row with "Have an account?" text and "Log in" button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,  // Center the text and button in the row
            verticalAlignment = Alignment.CenterVertically
        ) {
            // "Have an account?" text
            Text(
                text = "Have an account?",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between the text and the button

            // "Log in" button
            CustomTextButton(
                text = "Log in",
                onClick = onBackToLoginClick
            )
        }
    }
}


