package com.veldan.test_firebaserealtime.fragments.task_list.models

import com.veldan.test_firebaserealtime.room.models.TaskModelRoom

data class TaskModelDomain(
    val task: String = "",
    val startDate: StartDate? = null,
    val endDate: EndDate? = null,
)

/**
 * Сlasses included in parameters [TaskModelDomain].
 **/
data class StartDate(
    override val day: String = "",
    override val month: String = "",
    override val year: String = ""
) : Date()

data class EndDate(
    override val day: String = "",
    override val month: String = "",
    override val year: String = ""
) : Date()

abstract class Date {
    abstract val day: String
    abstract val month: String
    abstract val year: String
}

/**
 * [TaskModelDomain] extension functions.
 */
// {ext fun} .asTaskModelRoom
fun List<TaskModelDomain>.asTaskModelRoom(id: String): List<TaskModelRoom> {
    return this.map {
        TaskModelRoom(
            id = id,
            task = it.task,
//            startDate = it.startDate!!,
//            endDate = it.endDate!!,
        )
    }
}

