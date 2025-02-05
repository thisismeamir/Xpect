package com.thisismeamir.xpect.commands

import com.thisismeamir.xpect.TaskStatus
import picocli.CommandLine

@CommandLine.Command(
    name = "status",
    description = ["Change the status of a task"]
)
class ChangeStatusCommand : Runnable {
    @CommandLine.Parameters(index = "0", description = ["The task ID"])
    lateinit var taskId: String

    @CommandLine.Parameters(index = "1", description = ["The new task status"])
    lateinit var newStatus: TaskStatus

    override fun run() {
        val tasks = TaskManager.readTasks().toMutableList()
        val task = tasks.find { it.taskId == taskId }
        if (task != null) {
            tasks.remove(task)
            tasks.add(task.copy(taskStatus = newStatus))
            TaskManager.writeTasks(tasks)
            println("Task status changed to $newStatus: $task")
        } else {
            println("Task not found: $taskId")
        }
    }
}