package org.example.thirdwheel.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton( // Used for login and register buttons
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)  // Padding around the button, no shadow
            .fillMaxWidth() // Fill the available width
            .padding(vertical = 8.dp), // Padding inside the button
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF4BAFAD), // Slightly dark teal
            contentColor = Color.White            // White text color
        ),
        elevation = ButtonDefaults.elevation(0.dp), // No elevation for a flat look
        shape = RoundedCornerShape(24.dp) // Rounder edges (24.dp makes the button nicely rounded)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,             // Default font size for the button text
            fontWeight = FontWeight.Bold, // Bold text for emphasis
        )
    }
}

@Composable
fun CustomTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Color(0xFF007AFF),  // iOS-like blue color
    pressedTextColor: Color = Color(0xFF005EB8),  // Slightly darker when pressed
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Medium
) {
    var isPressed by remember { mutableStateOf(false) }

    ClickableText(
        text = AnnotatedString(text),
        modifier = modifier
            .padding(8.dp)
            .clickable(
                onClick = {
                    isPressed = true
                    onClick()
                }
            ),
        style = androidx.compose.ui.text.TextStyle(
            color = if (isPressed) pressedTextColor else textColor,
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            textDecoration = TextDecoration.None // No underline for iOS-style buttons
        ),
        onClick = {
            onClick()  // Handle click event
        }
    )

    // Reset press state after rendering the click state
    LaunchedEffect(isPressed) {
        isPressed = false
    }
}

@Composable
fun CloseIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Close, // Use the built-in close icon
            contentDescription = "Close", // Accessibility description
            modifier = Modifier.size(24.dp) // Set the icon size
        )
    }
}
