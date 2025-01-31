package com.thisismeamir.xpect.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTimeFormatted(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd-HH", Locale.getDefault())
    return formatter.format(Date()) // Get current time and format it
}