package com.example.goalgetter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>()
    val todoList: LiveData<List<TodoItem>> get() = _todoList

    init {
        _todoList.value = emptyList()
    }

    fun addTodoItem(todoItem: TodoItem) {
        val currentList = _todoList.value.orEmpty().toMutableList()
        currentList.add(todoItem)
        _todoList.value = currentList
    }

    fun updateTodoItem(todoItem: TodoItem) {
        val currentList = _todoList.value.orEmpty().toMutableList()
        val index = currentList.indexOfFirst { it.id == todoItem.id }
        if (index != -1) {
            currentList[index] = todoItem
            _todoList.value = currentList
        }
    }
}
