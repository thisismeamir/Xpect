package com.thisismeamir.xpect.commands

import picocli.CommandLine

@CommandLine.Command(
    name = "search",
    description = ["Search for a task"]
)
class SearchCommand : Runnable {
    @CommandLine.Parameters(index = "0", description = ["The task name"])
    lateinit var taskName: String

    override fun run() {
        val tasks = TaskManager.readTasks()
        val foundTasks = tasks.filter { it.title.contains(taskName, ignoreCase = true) }
        foundTasks.forEach { println(it) }
    }
}