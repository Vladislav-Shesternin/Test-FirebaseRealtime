package com.veldan.test_firebaserealtime.fragments.task_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.internal.LifecycleActivity
import com.google.android.gms.common.api.internal.LifecycleFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.veldan.test_firebaserealtime.databinding.FragmentTaskListBinding
import com.veldan.test_firebaserealtime.fragments.task_list.adapters.TaskListAdapter
import com.veldan.test_firebaserealtime.fragments.task_list.models.EndDate
import com.veldan.test_firebaserealtime.fragments.task_list.models.StartDate
import com.veldan.test_firebaserealtime.fragments.task_list.models.TaskModel

class TaskListFragment : Fragment() {
    private val TAG = this::class.simpleName

    // Binding
    private lateinit var binding: FragmentTaskListBinding

    // Firebase
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    // Components UI
    private lateinit var rvTaskList: RecyclerView

    // Components
    private lateinit var adapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initBinding()
        initFirebase()
        initAdapters()

        return binding.root
    }

    // {init}: Binding
    private fun initBinding() {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        initComponentsUI()
    }

    // {init}: Components UI
    private fun initComponentsUI() {
        binding.also {
            rvTaskList = it.rvTaskList
        }
    }

    // {init}: Firebase
    private fun initFirebase() {
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Tasks")
    }

    // {init}: Adapters
    private fun initAdapters() {
        initTaskListAdapter()
    }

    // {init}: TaskListAdapter
    private fun initTaskListAdapter() {
        adapter = TaskListAdapter()
        rvTaskList.adapter = adapter

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(TaskModel::class.java)
                adapter.submitList(listOf(value))
                Log.i(TAG, "onDataChange: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i(TAG, "onCancelled: ${error.message}")
            }

        })
    }
}