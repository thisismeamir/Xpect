package com.thisismeamir.xpect.utils

import java.util.*

// Subtract a specified number of days from the current Date and return the resulting Date
fun Date.subtractDays(days: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_YEAR, -days)
    return calendar.time
}