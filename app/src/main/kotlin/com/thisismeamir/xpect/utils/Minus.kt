package com.thisismeamir.xpect.utils

import java.util.*

// Subtract another date (in milliseconds) from the current Date and return the resulting Date
operator fun Date.minus(other: Date): Date {
    val result = this.time - other.time
    return Date(result)
}