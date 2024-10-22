package org.example.thirdwheel.screens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Minimalistic Light Theme Colors with Teal and White
val customColors = lightColors(
    primary = Color(0xFF80CBC4),           // Teal color as the primary color
    primaryVariant = Color(0xFF006666),    // Darker teal for variants
    secondary = Color(0xFF00BFA5),         // Light teal for accents
    background = Color(0xFFFFFFFF),        // Pure white background
    surface = Color(0xFFF7F7F7),           // Very light gray surface for cards, etc.
    onPrimary = Color.White,               // White text on primary buttons
    onSecondary = Color.White,             // White text on secondary buttons
    error = Color(0xFFB00020),             // Standard red for errors
    onBackground = Color.Black,            // Black text on white backgrounds
    onSurface = Color.Black                // Black text on light surfaces
)

// Minimalistic Typography Styles
val customTypography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Medium,    // Medium weight for headers
        fontSize = 30.sp,                  // Slightly smaller headers for minimalism
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,    // Normal weight for body text
        fontSize = 14.sp                   // Smaller body text to keep it minimal
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Light,     // Light weight for captions
        fontSize = 11.sp                   // Smaller size for captions
    )
)

// Minimalistic Rounded Shapes
val customShapes = Shapes(
    small = RoundedCornerShape(4.dp),    // Subtle rounding for small elements like buttons
    medium = RoundedCornerShape(8.dp),   // Slightly rounded corners for medium elements
    large = RoundedCornerShape(12.dp)    // Softer corners for large containers
)

// Custom MaterialTheme Wrapper
@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = customColors,
        typography = customTypography,
        shapes = customShapes,
        content = content
    )
}
