package com.thisismeamir.xpect.utils

import java.util.*

// Check if a Date is before another Date
infix fun Date.isBefore(other: Date): Boolean = this.before(other)