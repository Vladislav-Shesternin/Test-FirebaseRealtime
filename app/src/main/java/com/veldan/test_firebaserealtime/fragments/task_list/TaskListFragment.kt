package com.veldan.test_firebaserealtime.fragments.task_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchUIUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.veldan.test_firebaserealtime.databinding.FragmentTaskListBinding
import com.veldan.test_firebaserealtime.fragments.task_list.adapters.TaskListAdapter
import com.veldan.test_firebaserealtime.fragments.task_list.models.TaskModelDomain
import com.veldan.test_firebaserealtime.fragments.task_list.models.asTaskModelRoom
import com.veldan.test_firebaserealtime.fragments.task_list.view_models.TaskViewModel
import com.veldan.test_firebaserealtime.fragments.task_list.view_models.TaskViewModelFactory
import com.veldan.test_firebaserealtime.room.dao.TaskDao
import com.veldan.test_firebaserealtime.room.database.TaskDatabase
import com.veldan.test_firebaserealtime.room.models.asTaskModelDomain
import com.veldan.test_firebaserealtime.util.SwipeToDelete

class TaskListFragment : Fragment() {
    private val TAG = this::class.simpleName

    // Binding
    private lateinit var binding: FragmentTaskListBinding

    // Firebase
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    //Room DAO
    private lateinit var taskDao: TaskDao

    // ViewModels
    private lateinit var taskViewModel: TaskViewModel

    // Adapters
    private lateinit var adapter: TaskListAdapter

    // Components UI
    private lateinit var rvTaskList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initBinding()
        initFirebase()
        initRoomDao()

        return binding.root
    }

    // ------------------------------------------------------------| Binding |
    // {init}: Binding
    private fun initBinding() {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
        initComponentsUI() // <<< Components UI
    }

    // ------------------------------------------------------------| Components UI |
    // {init}: Components UI
    private fun initComponentsUI() {
        binding.also {
            rvTaskList = it.rvTaskList
        }
    }

    // ------------------------------------------------------------| Firebase |
    // {init}: Firebase
    private fun initFirebase() {
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Tasks")
    }

    // ------------------------------------------------------------| Room Dao |
    // {init}:
    private fun initRoomDao() {
        val application = requireNotNull(activity).application
        TaskDatabase.getDatabase(application).also { base ->
            taskDao = base.taskDao
        }
        initViewModels() // <<< ViewModels
    }

    // ------------------------------------------------------------| ViewModels |
    // {init}: ViewModels
    private fun initViewModels() {
        initTaskViewModel()
        initAdapters() // <<< Adapters
    }

    // {init fun}: TaskViewModel
    private fun initTaskViewModel() {
        val factoryTaskViewModel = TaskViewModelFactory(reference, taskDao)
        taskViewModel = ViewModelProvider(this, factoryTaskViewModel).get(TaskViewModel::class.java)
    }

    // ------------------------------------------------------------| RecyclerView Adapters |
    // {init}: Adapters
    private fun initAdapters() {
        initTaskListAdapter()
    }

    // {init fun}: TaskListAdapter
    private fun initTaskListAdapter() {
        adapter = TaskListAdapter()
        rvTaskList.adapter = adapter
        taskViewModel.liveTaskList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.asTaskModelDomain())
        })

        val itemTouchHelper = ItemTouchHelper(SwipeToDelete {
            val task: TaskModelDomain = adapter.currentList[it]
            taskViewModel.apply {
                // Delete from Room
                deleteTask(task.asTaskModelRoom())
                // Remove from Firebase
                removeTask(task.id)
            }
        })
        itemTouchHelper.attachToRecyclerView(rvTaskList)
    }
}