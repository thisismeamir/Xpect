package com.thisismeamir.xpect



import com.thisismeamir.xpect.XpectCLI
import picocli.CommandLine

fun main(args: Array<String>) {
    CommandLine(XpectCLI()).execute("new 'isk-marketing' do '06-02-2025'")
}