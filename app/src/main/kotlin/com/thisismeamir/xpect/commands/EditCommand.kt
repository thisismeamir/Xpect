package com.thisismeamir.xpect.commands

import com.thisismeamir.xpect.date.localDateToString
import picocli.CommandLine
import java.time.LocalDate

@CommandLine.Command(
    name = "edit",
    description = ["Edit a task"]
)
class EditCommand : Runnable {
    @CommandLine.Parameters(index = "0", description = ["The task ID"])
    lateinit var taskId: String

    @CommandLine.Parameters(index = "1", description = ["The new task name"], defaultValue = "")
    var newTaskName: String = ""

    @CommandLine.Parameters(index = "2", description = ["The new due date"], defaultValue = "")
    var newDueDate: String = ""

    override fun run() {
        val tasks = TaskManager.readTasks().toMutableList()
        val task = tasks.find { it.taskId == taskId }
        if (task != null) {
            tasks.remove(task)
            val updatedTask = task.copy(
                title = if (newTaskName.isNotEmpty()) newTaskName else task.title,
                dueDate = if (newDueDate.isNotEmpty()) LocalDate.parse(newDueDate).localDateToString() else task.dueDate
            )
            tasks.add(updatedTask)
            TaskManager.writeTasks(tasks)
            println("Task edited: $updatedTask")
        } else {
            println("Task not found: $taskId")
        }
    }
}