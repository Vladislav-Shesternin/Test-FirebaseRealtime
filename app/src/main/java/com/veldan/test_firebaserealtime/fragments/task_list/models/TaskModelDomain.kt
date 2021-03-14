package com.veldan.test_firebaserealtime.fragments.task_list.models

import com.veldan.test_firebaserealtime.room.models.EndDateRoom
import com.veldan.test_firebaserealtime.room.models.StartDateRoom
import com.veldan.test_firebaserealtime.room.models.TaskModelRoom
import com.veldan.test_firebaserealtime.util.Date

data class TaskModelDomain(
    var id: String = "",
    val task: String = "",
    val startDate: StartDateDomain? = null,
    val endDate: EndDateDomain? = null,
)

// ------------------------------------------------------------| Classes parameters of: [TaskModelDomain] |
data class StartDateDomain(
    override val day: String = "",
    override val month: String = "",
    override val year: String = ""
) : Date()

data class EndDateDomain(
    override val day: String = "",
    override val month: String = "",
    override val year: String = ""
) : Date()

// ------------------------------------------------------------| Extension functions of: [TaskModelDomain]  |
// {exs fun}: . externalFunctionName
fun TaskModelDomain.asTaskModelRoom(): TaskModelRoom {
    return TaskModelRoom(
        id = id,
        task = task,
        startDate = startDate!!.asStartDateRoom(),
        endDate = endDate!!.asEndDateRoom()
    )
}

// {ext fun} .asTaskModelRoom
fun List<TaskModelDomain>.asTaskModelRoom(): List<TaskModelRoom> {
    return this.map {
        TaskModelRoom(
            id = it.id,
            task = it.task,
            startDate = it.startDate!!.asStartDateRoom(),
            endDate = it.endDate!!.asEndDateRoom()
        )
    }
}

// {exs fun}: .asStartDateRoom
private fun StartDateDomain.asStartDateRoom() = StartDateRoom(day, month, year)

// {exs fun}: .asEndDateRoom
private fun EndDateDomain.asEndDateRoom() = EndDateRoom(day, month, year)


