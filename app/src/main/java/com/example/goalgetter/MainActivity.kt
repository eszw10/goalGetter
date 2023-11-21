package com.example.goalgetter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val todoViewModel: TodoViewModel by viewModels()
    private val ADD_TASK_REQUEST = 100
    private val EDIT_TASK_REQUEST = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.task_list_home)
        val todoAdapter = TodoViewAdapter(todoViewModel) { todo ->
            val intent = Intent(this, TaskEditActivity::class.java)
            intent.putExtra("todo", todo)
            startActivityForResult(intent, EDIT_TASK_REQUEST)
        }

        todoViewModel.todoList.observe(this, Observer { todos ->
            findViewById<TextView>(R.id.main_task_count).text = "${todos.size} Tasks"
            findViewById<TextView>(R.id.top_task_count).text = "${todos.size} Tasks"
            todoAdapter.submitList(todos)
        })

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<ImageButton>(R.id.back_button).isVisible = false
        findViewById<Button>(R.id.add_task_button).setOnClickListener {
            val intent = Intent(this, TaskEditActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }
    }

    private fun editTodo(todoItem: TodoItem) {

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK) {
            data?.getParcelableExtra<TodoItem>("todo")?.let { newTodo ->
                Log.d("debugg", newTodo.id);
                todoViewModel.addTodoItem(newTodo)
            }
        }

        if (requestCode == EDIT_TASK_REQUEST && resultCode == RESULT_OK) {
            data?.getParcelableExtra<TodoItem>("todo")?.let { updatedTodo ->
                Log.d("debugg", updatedTodo.id);
                todoViewModel.updateTodoItem(updatedTodo)
            }
        }
    }
}