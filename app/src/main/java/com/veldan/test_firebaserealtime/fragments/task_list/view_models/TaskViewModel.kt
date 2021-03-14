package com.veldan.test_firebaserealtime.fragments.task_list.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.veldan.test_firebaserealtime.fragments.task_list.models.*
import com.veldan.test_firebaserealtime.room.dao.TaskDao
import com.veldan.test_firebaserealtime.room.models.TaskModelRoom
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class TaskViewModel(
    private val reference: DatabaseReference,
    private val taskDao: TaskDao
) : ViewModel() {

    private val TAG = this::class.simpleName

    val liveTaskList: LiveData<List<TaskModelRoom>>
        get() = taskDao.getAllTasks()

    init {
        initFirebaseAddListener()
    }

    // ------------------------------------------------------------| Firebase Listeners |
    // {init}: Firebase AddListener
    private fun initFirebaseAddListener() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children =
                    requireNotNull(snapshot.children) { "The Reference = NULL" }

                val listTaskModelDomain: List<TaskModelDomain> = children.map { data ->
                    val taskModelDomain =
                        requireNotNull(data.getValue(TaskModelDomain::class.java)) {
                            "This DataSnapshot = NULL"
                        }
                    taskModelDomain.apply {
                        Log.i(TAG, "onDataChange: ${data.key}")
                        id = data.key!!
                        Log.i(TAG, "onDataChange:--- $id ")
                    }
                }

                if (listTaskModelDomain.isEmpty()) deleteAllTasks()
                else insertAllTasks(listTaskModelDomain.asTaskModelRoom())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i(TAG, "onCancelled: ${error.message}")
            }

        })
    }
    // ------------------------------------------------------------| fun Firebase |
    // {fun}: removeTask
    fun removeTask(id: String){
        reference.child(id).removeValue()
    }

    // {fun}: functionName
    private fun updateTask(){

    }

    // ------------------------------------------------------------| fun Room Dao |
    // {fun}: insertAllTasks
    fun insertAllTasks(taskList: List<TaskModelRoom>) {
        viewModelScope.launch {
            _insertAllTasks(taskList)
        }
    }

    // {fun}: deleteAllTasks
    fun deleteAllTasks() {
        viewModelScope.launch {
            _deleteAllTasks()
        }
    }

    // {fun}: deleteTask
    fun deleteTask(task: TaskModelRoom) {
        viewModelScope.launch {
            _deleteTask(task)
        }
    }

    // ------------------------------------------------------------| suspend fun Room Dao |
    // {suspend fun}: insertAllTasks
    private suspend fun _insertAllTasks(taskList: List<TaskModelRoom>) {
        taskDao.insertAllTasks(taskList)
    }

    // {suspend fun}: deleteAllTasks
    private suspend fun _deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    // {suspend fun}: functionName
    private suspend fun _deleteTask(task: TaskModelRoom) {
        taskDao.deleteTask(task)
    }
}