package com.veldan.test_firebaserealtime.fragments.task_list.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veldan.test_firebaserealtime.databinding.ItemTaskListBinding
import com.veldan.test_firebaserealtime.fragments.task_list.models.TaskModelDomain

/**
 * [TaskDiffCallback]
 *
 * */
class TaskDiffCallback : DiffUtil.ItemCallback<TaskModelDomain>() {
    override fun areItemsTheSame(oldItem: TaskModelDomain, newItem: TaskModelDomain): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: TaskModelDomain, newItem: TaskModelDomain): Boolean {
        return oldItem == newItem
    }

}

/**
 * [TaskListAdapter]
 * */
class TaskListAdapter :
    ListAdapter<TaskModelDomain, TaskListAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    /**
     * [TaskViewHolder]
     * */
    class TaskViewHolder private constructor(
        private val binding: ItemTaskListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemTaskListBinding.inflate(inflater, parent, false)
                return TaskViewHolder(binding)
            }
        }

        fun bind(task: TaskModelDomain) {

            binding.also { item ->
                item.etTask.setText(task.task)

                val startDate = requireNotNull(task.startDate) { "startDate = NULL" }
                startDate.also { date ->
                    item.etStartDay.setText(date.day)
                    item.etStartMonth.setText(date.month)
                    item.etStartYear.setText(date.year)
                }

                val endDate = requireNotNull(task.endDate) { "endDate = NULL" }
                endDate.also { date ->
                    item.etEndDay.setText(date.day)
                    item.etEndMonth.setText(date.month)
                    item.etEndYear.setText(date.year)
                }
            }
        }
    }
}