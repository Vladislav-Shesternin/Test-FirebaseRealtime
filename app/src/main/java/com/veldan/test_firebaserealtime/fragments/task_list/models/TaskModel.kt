package com.veldan.test_firebaserealtime.fragments.task_list.models

data class TaskModel(
    val task: String = "",
    val startDate: StartDate? = null,
    val endDate: EndDate? = null,
)

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
