package com.thisismeamir.xpect.utils
import com.thisismeamir.xpect.tokens.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(): Date? {
    return try {
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        formatter.parse(this)
    } catch (e: Exception) {
        println("Error parsing date: ${e.message}")
        null
    }
}


