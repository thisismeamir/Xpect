package com.thisismeamir.xpect

import com.thisismeamir.xpect.commands.*
import picocli.CommandLine

@CommandLine.Command(
    name = "xpect",
    description = ["Xavier Productivity Engine for Coordination of Tasks"],
    mixinStandardHelpOptions = true,
    version = ["Xpect 1.0"],
    subcommands = [
        NewTaskCommand::class,
        AgendaCommand::class,
        ChangeStatusCommand::class,
        DeleteCommand::class,
        EditCommand::class,
        SearchCommand::class
    ]
)
class XpectCLI

