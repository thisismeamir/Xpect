package com.thisismeamir.xpect.configs

import com.thisismeamir.app.com.thisismeamir.xpect.sprint.task.TaskStatus
import com.thisismeamir.xpect.sprint.Sprint
import com.thisismeamir.xpect.sprint.task.Task

fun Sprint.checkForUndoneTasks(): List<Task> {
    return this.tasks.filter { it.status != TaskStatus.DONE || it.status != TaskStatus.CANCELLED || it.status != TaskStatus.REJECTED }
}