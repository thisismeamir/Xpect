package com.thisismeamir.xpect.sprint

import com.thisismeamir.app.com.thisismeamir.xpect.sprint.task.TaskStatus
import com.thisismeamir.xpect.sprint.project.Project
import com.thisismeamir.xpect.sprint.task.Task
import com.thisismeamir.xpect.sprint.task.TaskPriority
import com.thisismeamir.xpect.sprint.task.TaskType
import com.thisismeamir.xpect.tokens.STORAGE_DIR
import com.thisismeamir.xpect.utils.dateToString
import com.thisismeamir.xpect.utils.ensureStorageDir
import com.thisismeamir.xpect.utils.plus
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import java.util.Date

@Serializable
data class Sprint(
    val startDate: String,
    val endDate: String,
    var tasks: MutableList<Task> = mutableListOf(),
    var sprintStatus: SprintStatus
) {

    fun addTask(task: Task){
        tasks.add(task)
        this.saveToFile()
    }

    fun removeTask(task: Task){
        tasks.remove(task)
        this.saveToFile()
    }

    fun setTaskStatus(index: Int, taskStatus: TaskStatus){
        tasks[index].changeStatus(taskStatus)
        this.saveToFile()
    }


    fun setTaskDueDate(index: Int, dueDate: String){
        tasks[index].changeDueDate(dueDate)
        this.saveToFile()
    }

    fun setTaskPriority(index: Int, taskPriority: TaskPriority){
        tasks[index].changeTaskPriority(taskPriority)
        this.saveToFile()
    }

    fun setTaskTitle(index: Int, title: String){
        tasks[index].changeTitle(title)
        this.saveToFile()
    }

    fun setTaskDescription(index: Int, description: String){
        tasks[index].changeDescription(description)
        this.saveToFile()
    }

    fun setTaskProject(index: Int, project: Project){
        tasks[index].changeProject(project)
        this.saveToFile()
    }

    fun setTaskType(index: Int, type: List<TaskType>){
        tasks[index].changeType(type)
        this.saveToFile()
    }

    fun setStatus(sprintStatus: SprintStatus){
        this.sprintStatus = sprintStatus
        this.saveToFile()
    }

    // Convert Sprint to JSON and save it
    fun saveToFile() {
        ensureStorageDir()
        val sprintFile = File("$STORAGE_DIR/sprints/sprint_${endDate}.json")
        sprintFile.writeText(Json.encodeToString(this))
    }

    // Companion object to load Sprint from JSON
    companion object {
        fun loadFromFile(endDate: Date): Sprint {
            val sprintFile = File("$STORAGE_DIR/sprints/sprint_${endDate.dateToString()}.json")
             if (sprintFile.exists()) {
                 return Json.decodeFromString<Sprint>(sprintFile.readText())
            } else {
                sprintFile.createNewFile()
                return Sprint(
                    startDate = endDate.dateToString(),
                    endDate = (endDate + 7).dateToString(),
                    sprintStatus = SprintStatus.CURRENT
                )
            }
        }
    }
}
