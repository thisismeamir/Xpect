package com.thisismeamir.xpect

import com.thisismeamir.app.com.thisismeamir.xpect.sprint.task.TaskStatus
import com.thisismeamir.xpect.sprint.Sprint
import com.thisismeamir.xpect.sprint.SprintStatus
import com.thisismeamir.xpect.sprint.project.Project
import com.thisismeamir.xpect.sprint.project.ProjectStatus
import com.thisismeamir.xpect.sprint.task.Task
import com.thisismeamir.xpect.sprint.task.TaskPriority
import com.thisismeamir.xpect.sprint.task.TaskType
import com.thisismeamir.xpect.tokens.ALL_SPRINT_NAMES
import com.thisismeamir.xpect.tokens.STORAGE_DIR
import com.thisismeamir.xpect.utils.dateToString
import com.thisismeamir.xpect.utils.ensureStorageDir
import com.thisismeamir.xpect.utils.plus
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

@Serializable
data class Xpect(
    val sprints: MutableList<Pair<String,String>> = mutableListOf()
) {


    fun getCurrentSprint(): Sprint {
        TODO()
    }

    fun getAllTasks(): List<Task> {
        TODO()
    }

    fun loadFromFile(): Xpect {
        val xpectFile = File(ALL_SPRINT_NAMES)
        return if (xpectFile.exists()) {
            Json.decodeFromString<Xpect>(xpectFile.readText())
        } else {
            xpectFile.createNewFile()
            val window: Pair<Date, Date> = Date() to (Date() + 7)
            val newSprint: Sprint = Sprint(
                startDate = window.first.dateToString(),
                endDate = window.second.dateToString(),
                sprintStatus = SprintStatus.CURRENT
            )

            Json.decodeFromString<Xpect>(Json.encodeToString(this))
        }
    }

    fun saveToFile() {
        ensureStorageDir()
        val sprintFile = File("$STORAGE_DIR/xpect.json")
        sprintFile.writeText(Json.encodeToString(this))
    }
}


fun main() {
    val xpect = Xpect()
    xpect.loadFromFile()
    println(xpect.loadFromFile().sprints.last())
}