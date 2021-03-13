package com.veldan.test_firebaserealtime.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.veldan.test_firebaserealtime.room.models.TaskModelRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllTasks(taskList: List<TaskModelRoom>)

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): Flow<List<TaskModelRoom>>
}