package com.veldan.test_firebaserealtime.room.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veldan.test_firebaserealtime.fragments.task_list.models.EndDate
import com.veldan.test_firebaserealtime.fragments.task_list.models.StartDate

@Entity(tableName = "task_table")
data class TaskModelRoom(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "task")
    val task: String,

    // RRR EEE LLL AAA TTT III OOO NNN
//    @Embedded
//    val startDate: StartDate,
//
//    @Embedded
//    val endDate: EndDate
)