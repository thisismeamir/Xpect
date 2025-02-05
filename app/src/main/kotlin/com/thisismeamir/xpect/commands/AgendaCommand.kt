package com.thisismeamir.xpect.commands

import picocli.CommandLine

@CommandLine.Command(
    name = "agenda",
    description = ["List all tasks"]
)
class AgendaCommand : Runnable {
    @CommandLine.Parameters(index = "0", description = ["Number of tasks to show"], defaultValue = "10")
    var numberOfTasks: Int = 10

    override fun run() {
        val tasks = TaskManager.readTasks()
        tasks.take(numberOfTasks).forEach { println(it) }
    }
}