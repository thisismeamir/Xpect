package com.thisismeamir.xpect.commands

import com.thisismeamir.xpect.Task
import com.thisismeamir.xpect.TaskStatus
import com.thisismeamir.xpect.TaskType
import com.thisismeamir.xpect.date.localDateToString
import picocli.CommandLine
import java.time.LocalDate
import java.util.*

@CommandLine.Command(
    name = "new",
    description = ["Create a new task"]
)
class NewTaskCommand : Runnable {
    @CommandLine.Parameters(index = "0", description = ["The project name"])
    lateinit var projectName: String

    @CommandLine.Parameters(index = "1", description = ["The task name"])
    lateinit var taskName: String

    @CommandLine.Parameters(index = "2", description = ["The due date"])
    lateinit var dueDate: String

    @CommandLine.Parameters(index = "3", description = ["Type"])
    lateinit var taskType: String

    override fun run() {
        val tasks = TaskManager.readTasks().toMutableList()
        val newTask = Task(
            taskId = UUID.randomUUID().toString(),
            dueDate = LocalDate.parse(dueDate).localDateToString(),
            title = taskName,
            project = projectName,
            taskStatus = TaskStatus.BACKLOG // Default task status
        )
        tasks.add(newTask)
        TaskManager.writeTasks(tasks)
        println("Task created: $newTask")
    }
}