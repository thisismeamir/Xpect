package com.thisismeamir.xpect.sprint.project

import kotlinx.serialization.Serializable

@Serializable
enum class ProjectStatus(name:String) {
    PLANNING("planning"),
    IN_PROGRESS("in-progress"),
    REVIEW("review"),
    DONE("done"),
    CANCELED("canceled")
}