package com.thisismeamir.xpect.utils

import com.thisismeamir.xpect.tokens.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun Date.dateToString(): String {
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return formatter.format(date)
}