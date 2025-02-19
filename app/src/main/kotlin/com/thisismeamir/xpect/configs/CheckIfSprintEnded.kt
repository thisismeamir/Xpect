package com.thisismeamir.xpect.configs

import com.thisismeamir.xpect.sprint.Sprint
import com.thisismeamir.xpect.utils.isBefore
import com.thisismeamir.xpect.utils.plus
import com.thisismeamir.xpect.utils.toDate
import java.util.*

fun Sprint.checkIfSprintEnded(){
    val today = Date()
    if(this.endDate.toDate()?.isBefore(today) == true){
        val tasksUndone = this.checkForUndoneTasks()
        val planningSprint = Sprint.loadFromFile((this.endDate.toDate()!! + 7))
        tasksUndone.forEach {
            planningSprint?.addTask(it)
        }
    }

}