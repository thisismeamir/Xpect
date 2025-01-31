package com.thisismeamir.xpect.utils

import java.util.*

// Add another date (in milliseconds) to the current Date and return the resulting Date
operator fun Date.plus(other: Date): Date {
    val result = this.time + other.time
    return Date(result)
}