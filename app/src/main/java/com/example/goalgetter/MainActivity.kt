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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val todoViewModel: TodoViewModel by viewModels()
    private var dateFilter = LocalDate.now()
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
            val filteredTodos = filterTodo(todos)
            findViewById<TextView>(R.id.main_task_count).text = "${filteredTodos.size} Tasks"
            findViewById<TextView>(R.id.top_task_count).text = "${filteredTodos.size} Tasks"
            todoAdapter.submitList(filteredTodos)
        })

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<ImageButton>(R.id.back_button).isVisible = false
        findViewById<Button>(R.id.add_task_button).setOnClickListener {
            val intent = Intent(this, TaskEditActivity::class.java)
            intent.putExtra("date", dateFilter)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }

        setDateView()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK) {
            data?.getParcelableExtra<TodoItem>("todo")?.let { newTodo ->
                todoViewModel.addTodoItem(newTodo)
            }
        }

        if (requestCode == EDIT_TASK_REQUEST && resultCode == RESULT_OK) {
            data?.getParcelableExtra<TodoItem>("todo")?.let { updatedTodo ->
                todoViewModel.updateTodoItem(updatedTodo)
            }
        }
    }

    fun filterTodo(todos: List<TodoItem>): List<TodoItem> {
        return todos.filter { todo -> todo.date == dateFilter }
    }

    fun setDateView() {
        val currentDate = LocalDate.now()
        val longFormatter = DateTimeFormatter.ofPattern("dd MMMM")
        findViewById<TextView>(R.id.app_bar_title).text = longFormatter.format(currentDate)

        val shortFormatter = DateTimeFormatter.ofPattern("dd\nMMM")
        val buttons = arrayOf(
            findViewById<Button>(R.id.button_date1),
            findViewById<Button>(R.id.button_date2),
            findViewById<Button>(R.id.button_date3),
            findViewById<Button>(R.id.button_date4)
        )

        for ((index, button) in buttons.withIndex()) {
            val date = currentDate.plusDays(index.toLong())
            button.text = shortFormatter.format(date)
            button.setOnClickListener {
                dateFilter = date
                findViewById<TextView>(R.id.app_bar_title).text = longFormatter.format(date)
                todoViewModel.refresh()
            }
        }
    }
}