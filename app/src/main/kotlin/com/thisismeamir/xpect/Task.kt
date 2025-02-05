package com.thisismeamir.xpect
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Task(
    val taskId: String,
    val dueDate: String,
    val title: String,
    val project: String,
    val taskStatus: TaskStatus
)

