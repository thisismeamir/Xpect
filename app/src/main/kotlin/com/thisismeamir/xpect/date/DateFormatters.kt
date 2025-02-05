package com.thisismeamir.xpect.date

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.stringToLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return LocalDate.parse(this, formatter)
}

fun LocalDate.localDateToString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return this.format(formatter)
}