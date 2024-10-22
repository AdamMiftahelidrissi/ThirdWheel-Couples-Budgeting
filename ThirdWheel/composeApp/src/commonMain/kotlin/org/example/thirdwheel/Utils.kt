package org.example.thirdwheel

import kotlin.math.round

// Helper function to format currency with commas
fun formatCurrency(amount: Double): String {
    return "$${addCommasToNumber(roundToTwoDecimals(amount))}"
}

// Helper function to round a double to two decimal places
fun roundToTwoDecimals(value: Double): Double {
    return (round(value * 100) / 100) // Ensures two decimal places
}

// Helper function to add commas to the whole part of the number
fun addCommasToNumber(value: Double): String {
    val parts = value.toString().split(".") // Split into whole and decimal parts
    val wholePart = parts[0]
    val decimalPart = if (parts.size > 1) parts[1] else "00" // Handle decimal part

    val wholeWithCommas = wholePart.reversed().chunked(3).joinToString(",").reversed()

    // Ensure the decimal part is always two digits
    val formattedDecimal = if (decimalPart.length == 1) "${decimalPart}0" else decimalPart

    return "$wholeWithCommas.$formattedDecimal"
}