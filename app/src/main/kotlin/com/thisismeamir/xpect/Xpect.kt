package com.thisismeamir.xpect

import com.thisismeamir.app.com.thisismeamir.xpect.sprint.task.TaskStatus
import com.thisismeamir.xpect.configs.checkForUndoneTasks
import com.thisismeamir.xpect.sprint.Sprint
import com.thisismeamir.xpect.sprint.SprintStatus
import com.thisismeamir.xpect.sprint.project.Project
import com.thisismeamir.xpect.sprint.project.ProjectStatus
import com.thisismeamir.xpect.sprint.task.Task
import com.thisismeamir.xpect.sprint.task.TaskPriority
import com.thisismeamir.xpect.sprint.task.TaskType
import com.thisismeamir.xpect.tokens.ALL_SPRINT_NAMES
import com.thisismeamir.xpect.tokens.STORAGE_DIR
import com.thisismeamir.xpect.utils.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

@Serializable
data class Xpect(
    val sprints: MutableList<Pair<String, String>> = mutableListOf()
) {
    private fun addSprint(sprint: Sprint) {
        sprints.add(sprint.startDate to sprint.endDate)
        sprint.saveToFile()
        saveToFile()
    }

    fun getSprintTasks(endDate: Date): List<Task> {
        val sprint = Sprint.loadFromFile(endDate)
        return sprint.tasks
    }


    fun getAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        sprints.map { it.second }.forEach {
            val sprint = it.toDate()?.let { it1 -> Sprint.loadFromFile(it1) }
            sprint?.tasks?.forEach { task ->
                tasks.add(task)
            }
        }
        return tasks
    }

    fun getAllUndoneTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        sprints.map { it.second }.forEach {
            val sprint = it.toDate()?.let { it1 -> Sprint.loadFromFile(it1) }
            sprint?.checkForUndoneTasks()?.forEach { task ->
                tasks.add(task)
            }
        }
        return tasks
    }

    fun addTask(task: Task, dueDate: Date) {
        sprints.map {
            it.first.toDate() to it.second.toDate()
        }.forEach {
            if (it.first?.isBefore(dueDate) == true && it.second?.isAfter(dueDate) == true) {
                val sprint = Sprint.loadFromFile(it.second!!)
                sprint.addTask(task)
            }
        }
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
            this.addSprint(newSprint)
            Json.decodeFromString<Xpect>(Json.encodeToString(this))
        }
    }

    fun saveToFile() {
        ensureStorageDir()
        val xpectFile = File("$STORAGE_DIR/xpect.json")
        xpectFile.writeText(Json.encodeToString(this))
    }
}


