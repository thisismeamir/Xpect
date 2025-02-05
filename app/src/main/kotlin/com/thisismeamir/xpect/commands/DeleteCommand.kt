package com.thisismeamir.xpect.commands

import picocli.CommandLine

@CommandLine.Command(
    name = "delete",
    description = ["Delete a task"]
)
class DeleteCommand : Runnable {
    @CommandLine.Parameters(index = "0", description = ["The task ID"])
    lateinit var taskId: String

    override fun run() {
        val tasks = TaskManager.readTasks().toMutableList()
        val task = tasks.find { it.taskId == taskId }
        if (task != null) {
            tasks.remove(task)
            TaskManager.writeTasks(tasks)
            println("Task deleted: $task")
        } else {
            println("Task not found: $taskId")
        }
    }
}