package com.example.goalgetter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class TodoViewAdapter(
    private val todoViewModel: TodoViewModel,
    private val editTodo: (TodoItem) -> Unit
) : ListAdapter<TodoItem, TodoViewHolder>(TodoItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_strip, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var todo = getItem(position)
        holder.titleView.text = todo.task
        holder.descriptionView.text = todo.description
        holder.doneTaskView.isChecked = todo.done
        holder.timeView.text = "${todo.start}\n${todo.end}"

        holder.doneTaskView.setOnCheckedChangeListener { _, isChecked ->
            todo.done = isChecked
            todoViewModel.updateTodoItem(todo)
        }
        holder.wrapper.setOnClickListener {
            editTodo(todo)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class TodoItemDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }
}
