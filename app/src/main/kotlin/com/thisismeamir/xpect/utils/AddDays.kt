package com.thisismeamir.xpect.utils

import java.util.*

// Add a specified number of days to the current Date and return the resulting Date
operator fun Date.plus(days: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_YEAR, days)
    return calendar.time
}