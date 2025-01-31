package com.thisismeamir.xpect.sprint.project

import com.thisismeamir.xpect.sprint.project.ProjectStatus
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    var title: String,
    var description: String,
    var projectStatus: ProjectStatus
)

