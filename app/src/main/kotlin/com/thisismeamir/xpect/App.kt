package com.thisismeamir.xpect



import com.thisismeamir.xpect.commands.XpectCLI
import picocli.CommandLine

fun main(args: Array<String>) {
    CommandLine(XpectCLI()).execute(*args)
}