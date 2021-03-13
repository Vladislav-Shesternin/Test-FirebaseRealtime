package com.veldan.test_firebaserealtime.fragments.task_list.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.veldan.test_firebaserealtime.room.dao.TaskDao
import java.lang.IllegalArgumentException

class TaskViewModelFactory(
    private val reference: DatabaseReference,
    private val taskDao: TaskDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(reference, taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}