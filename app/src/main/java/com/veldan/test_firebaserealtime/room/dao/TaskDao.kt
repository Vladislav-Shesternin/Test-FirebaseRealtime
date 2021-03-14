package com.veldan.test_firebaserealtime.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.veldan.test_firebaserealtime.room.models.TaskModelRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllTasks(taskList: List<TaskModelRoom>)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Delete
    suspend fun deleteTask(task: TaskModelRoom)

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<TaskModelRoom>>
}