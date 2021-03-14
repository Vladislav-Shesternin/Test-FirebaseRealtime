package com.veldan.test_firebaserealtime.room.models

import androidx.room.*
import com.veldan.test_firebaserealtime.fragments.task_list.models.EndDateDomain
import com.veldan.test_firebaserealtime.fragments.task_list.models.StartDateDomain
import com.veldan.test_firebaserealtime.fragments.task_list.models.TaskModelDomain
import com.veldan.test_firebaserealtime.util.Date

@Entity(tableName = "task_table")
data class TaskModelRoom(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "task")
    val task: String,

    @Embedded(prefix = "start")
    val startDate: StartDateRoom,

    @Embedded(prefix = "end")
    val endDate: EndDateRoom,
)

// ------------------------------------------------------------| Classes parameters of: [TaskModelRoom] |
data class StartDateRoom(
    override val day: String,
    override val month: String,
    override val year: String,
) : Date()

data class EndDateRoom(
    override val day: String,
    override val month: String,
    override val year: String,
) : Date()

// ------------------------------------------------------------| Extension functions of: [TaskModelRoom]  |
// {ext fun} .asTaskModelDomain
fun List<TaskModelRoom>.asTaskModelDomain(): List<TaskModelDomain> {
    return this.map {
        TaskModelDomain(
            task = it.task,
            startDate = it.startDate.asStartDateDomain(),
            endDate = it.endDate.asEndDateDomain()
        )
    }
}

// {exs fun}: .asStartDateDomain
private fun StartDateRoom.asStartDateDomain() = StartDateDomain(day, month, year)

// {exs fun}: .asEndDateDomain
private fun EndDateRoom.asEndDateDomain() = EndDateDomain(day, month, year)
