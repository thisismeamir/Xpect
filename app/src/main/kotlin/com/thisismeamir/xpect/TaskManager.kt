import com.thisismeamir.xpect.Task
import com.thisismeamir.xpect.TaskStatus
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object TaskManager {
    private const val FILENAME = "/home/kid-a/xpect/xpect.json"
    private const val FILEOFFINISHEDTASKS = "/home/kid-a/xpect/xpect-done.json"
    private val json = Json { prettyPrint = true }

    fun readTasks(): List<Task> {
        return if (File(FILENAME).exists()) {
            json.decodeFromString(File(FILENAME).readText())
        } else {
            emptyList()
        }
    }

    fun writeTasks(tasks: List<Task>) {
        val finished: List<Task> =
            tasks.filter { it.taskStatus == TaskStatus.DONE || it.taskStatus == TaskStatus.REVIEW || it.taskStatus == TaskStatus.CANCELED || it.taskStatus == TaskStatus.ARCHIVED }
        val unfinished: List<Task> =
            tasks.filterNot { it.taskStatus == TaskStatus.DONE || it.taskStatus == TaskStatus.REVIEW || it.taskStatus == TaskStatus.CANCELED || it.taskStatus == TaskStatus.ARCHIVED }

        File(FILENAME).writeText(json.encodeToString(unfinished))
        File(FILEOFFINISHEDTASKS).writeText(json.encodeToString(finished))
    }
}