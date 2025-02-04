package com.thisismeamir.xpect.sprint.task

import com.thisismeamir.app.com.thisismeamir.xpect.sprint.task.TaskStatus
import com.thisismeamir.xpect.sprint.project.Project
import com.thisismeamir.xpect.tokens.STORAGE_DIR
import com.thisismeamir.xpect.utils.dateToString
import com.thisismeamir.xpect.utils.ensureStorageDir
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import java.io.File
import java.util.Date

@Serializable
data class Task (
    var title: String,
    var description: String = "",
    var project: Project,
    var type: List<TaskType>,
    var status: TaskStatus,
    var taskPriority: TaskPriority,
    var dueDate: String,
) {

    fun changeTitle(title: String){
        this.title = title

        this.saveToFile()
    }

    fun changeDescription(description: String){
        this.description = description
        this.saveToFile()
    }

    fun changeProject(project: Project){
        this.project = project
        this.saveToFile()
    }

    fun changeType(type: List<TaskType>){
        this.type = type
        this.saveToFile()
    }

    fun changeStatus(status: TaskStatus){
        this.status = status
        this.saveToFile()
    }

    fun changeTaskPriority(taskPriority: TaskPriority){
        this.taskPriority = taskPriority
        this.saveToFile()
    }

    fun changeDueDate(dueDate: String){
        this.dueDate = dueDate
        this.saveToFile()
    }




    // Convert the Task to JSON and save it
    fun saveToFile() {
        ensureStorageDir()
        val taskFile = File("$STORAGE_DIR/tasks/task_${title.replace(" ", "_")}.json")
        taskFile.writeText(Json.encodeToString(this))
    }

    // Companion object to load a Task from JSON
    companion object {
        fun loadFromFile(taskTitle: String): Task? {
            val taskFile = File("$STORAGE_DIR/tasks/task_${taskTitle.replace(" ", "_")}.json")
            return if (taskFile.exists()) {
                Json.decodeFromString<Task>(taskFile.readText())
            } else {
                null
            }
        }
    }
}
