package com.veldan.test_firebaserealtime.fragments.task_list.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.veldan.test_firebaserealtime.fragments.task_list.models.EndDate
import com.veldan.test_firebaserealtime.fragments.task_list.models.StartDate
import com.veldan.test_firebaserealtime.fragments.task_list.models.TaskModelDomain
import com.veldan.test_firebaserealtime.room.dao.TaskDao
import com.veldan.test_firebaserealtime.room.models.TaskModelRoom
import kotlinx.coroutines.launch

class TaskViewModel(
    private val reference: DatabaseReference,
    private val taskDao: TaskDao
) : ViewModel() {

    private val TAG = this::class.simpleName

    init {
        viewModelScope.launch {
            initFirebaseAddListener()
        }
    }

    // {suspend init}: Firebase AddListener
    private suspend fun initFirebaseAddListener() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {
                Log.i(TAG, "onCancelled: ${error.message}")
            }

        })
    }

    // {fun}: functionName
    private suspend fun insertAllTasks(taskList: List<TaskModelRoom>) {
        taskDao.insertAllTasks(taskList)
    }
}