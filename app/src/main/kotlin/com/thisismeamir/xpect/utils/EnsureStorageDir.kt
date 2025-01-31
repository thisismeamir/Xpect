package com.thisismeamir.xpect.utils

import com.thisismeamir.xpect.tokens.STORAGE_DIR
import java.io.File

// Ensure folder exists
fun ensureStorageDir() {
    val dir = File(STORAGE_DIR)
    if (!dir.exists()) dir.mkdirs()
}