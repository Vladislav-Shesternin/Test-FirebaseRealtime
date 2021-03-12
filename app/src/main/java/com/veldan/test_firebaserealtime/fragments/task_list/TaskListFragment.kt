package com.veldan.test_firebaserealtime.fragments.task_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.google.android.gms.common.api.internal.LifecycleActivity
import com.google.android.gms.common.api.internal.LifecycleFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.veldan.test_firebaserealtime.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment() {
    private val TAG = this::class.simpleName

    // Binding
    private lateinit var binding: FragmentTaskListBinding

    // Firebase
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initBinding()
        initFirebase()

        return binding.root
    }

    // {init}: Binding
    private fun initBinding() {
        binding = FragmentTaskListBinding.inflate(layoutInflater)
    }

    // {init}: Firebase
    private fun initFirebase() {
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Tasks")
    }

    // {fun}: sendDate
    private fun sendData() {
        val task = "Владислав... Стань Android Developer"
        val dataStart = "12.02.00"
        val dataEnd = "10.04.21"
    }
}