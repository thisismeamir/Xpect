package com.thisismeamir.xpect.utils

import java.util.*

// Check if a Date is after another Date
infix fun Date.isAfter(other: Date): Boolean = this.after(other)