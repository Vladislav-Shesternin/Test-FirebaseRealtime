package com.veldan.test_firebaserealtime.room.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veldan.test_firebaserealtime.fragments.task_list.models.EndDate
import com.veldan.test_firebaserealtime.fragments.task_list.models.StartDate

@Entity(tableName = "task_table")
data class TaskModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "task")
    val task: String,

    @Embedded
    val startDate: StartDate,

    @Embedded
    val endDate: EndDate
)