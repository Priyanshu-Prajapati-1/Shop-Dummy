package com.example.shopdummy.utils

import java.text.DecimalFormat

fun formatRating(number: Double): String {
    val decimalFormat = DecimalFormat("0.0")
    return decimalFormat.format(number)
}