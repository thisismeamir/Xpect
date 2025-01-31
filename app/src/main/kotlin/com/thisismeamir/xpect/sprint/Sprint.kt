package com.thisismeamir.xpect.sprint

import com.thisismeamir.xpect.sprint.task.Task
import com.thisismeamir.xpect.tokens.STORAGE_DIR
import com.thisismeamir.xpect.utils.ensureStorageDir
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import java.util.Date

@Serializable
data class Sprint(
    val startDate: String,
    val endDate: String,
    var tasks: MutableList<Task> = mutableListOf(),
    val sprintStatus: SprintStatus
) {
    // Convert Sprint to JSON and save it
    fun saveToFile() {
        ensureStorageDir()
        val sprintFile = File("$STORAGE_DIR/sprint_${endDate}.json")
        sprintFile.writeText(Json.encodeToString(this))
    }

    fun addTask(task: Task){
        tasks.add(task)
        this.saveToFile()
    }
    // Companion object to load Sprint from JSON
    companion object {
        fun loadFromFile(endDate: Date): Sprint? {
            val sprintFile = File("$STORAGE_DIR/sprint_${endDate}.json")
            return if (sprintFile.exists()) {
                Json.decodeFromString<Sprint>(sprintFile.readText())
            } else {
                null
            }
        }
    }
}
