package com.thisismeamir.xpect.commands

import com.thisismeamir.xpect.Xpect
import picocli.CommandLine
import java.util.Date
import java.util.concurrent.Callable

@CommandLine.Command(
    name = "xpect",
    mixinStandardHelpOptions = true,
    version = ["xpect 1.0"],
    description = ["Xavier's Project Estimation and Control Tool"]
)
class XpectCLI: Callable<Int> {
    @CommandLine.Option(
        names = ["-v", "--version"],
        description = ["Print version information"]
    )
    var versionRequested = false

    @CommandLine.Option(
        names = ["-a", "--agenda"],
        description = ["Agenda for the sprint"]
    )
    var agendaRequested = false


    override fun call(): Int {
        if (versionRequested) {
            println("xpect 1.0")
        }
        else if (agendaRequested) {
            val today = Date()
            Xpect().getSprintTasks(today).forEach {
                println(it.title)
            }
        }
        else {
            CommandLine.usage(this, System.out)
        }
        return 0
    }
}