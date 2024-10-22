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
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onBackToLoginClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }  // Control password visibility
    var showConfirmPassword by remember { mutableStateOf(false) }  // Control confirm password visibility
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Email validation regex pattern
    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Screen title
        Text(
            text = "Create Account",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color(0xFF4BAFAD)
        )

        Spacer(modifier = Modifier.height(24.dp)) //Space between header and name textfield

        // first name field
        CustomTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = "Name",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(16.dp)) // space between name and email

        // Email Input Field
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email Address",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(16.dp)) //spacing between email and password fields

        // Password Input Field with visibility toggle
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            isPasswordField = true,
            showPassword = showPassword,
            onShowPasswordToggle = { showPassword = !showPassword }  // Toggle password visibility
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Input Field with visibility toggle
        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "Confirm Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPasswordField = true,
            showPassword = showConfirmPassword,
            onShowPasswordToggle = { showConfirmPassword = !showConfirmPassword }  // Toggle confirm password visibility
        )

        Spacer(modifier = Modifier.height(20.dp)) // Space between confirm password and sign up button

        // Show error message if validation fails
        if (showError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Sign Up Button with Validation Logic
        CustomButton(
            text = "Sign Up",
            onClick = {
                if (!emailPattern.matches(email)) {
                    showError = true
                    errorMessage = "Invalid email address"
                } else if (password != confirmPassword) {
                    showError = true
                    errorMessage = "Passwords do not match"
                } else if (password.length < 6) {
                    showError = true
                    errorMessage = "Password must be at least 6 characters"
                } else {
                    showError = false
                    onSignUpSuccess()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row with "Have an account?" text and "Log in" button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Have an account?",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomTextButton(
                text = "Log in",
                onClick = onBackToLoginClick
            )
        }
    }
}

