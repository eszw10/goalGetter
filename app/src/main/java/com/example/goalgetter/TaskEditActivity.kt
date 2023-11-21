package com.example.goalgetter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.w3c.dom.Text
import java.time.LocalDate
import kotlin.random.Random

class TaskEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_editor)

        findViewById<ImageButton>(R.id.calendar_button).isVisible = false
        findViewById<TextView>(R.id.app_bar_title).text = "Add Task"

        val hasEditTodo = intent.hasExtra("todo")
        if (hasEditTodo) {
            val editTodo = intent.getParcelableExtra("todo", TodoItem::class.java)
            findViewById<EditText>(R.id.task_title_input).setText(editTodo?.task)
            findViewById<EditText>(R.id.task_description_input).setText(editTodo?.description)
            findViewById<EditText>(R.id.task_start_input).setText(editTodo?.start)
            findViewById<EditText>(R.id.task_end_input).setText(editTodo?.end)
            findViewById<TextView>(R.id.app_bar_title).text = "Edit Task"
        }

        findViewById<ImageButton>(R.id.back_button).setOnClickListener{
            finish()
        }

        findViewById<Button>(R.id.submit_todo_button).setOnClickListener{
            var todo = TodoItem(
                generateID(12),
                findViewById<EditText>(R.id.task_title_input).text.toString(),
                findViewById<EditText>(R.id.task_description_input).text.toString(),
                LocalDate.now(),
                false,
                findViewById<EditText>(R.id.task_start_input).text.toString(),
                findViewById<EditText>(R.id.task_end_input).text.toString(),
            )

            if (hasEditTodo) {
                val editTodo = intent.getParcelableExtra("todo", TodoItem::class.java)
                editTodo?.task = todo.task
                editTodo?.description = todo.description
                editTodo?.start = todo.start
                editTodo?.end = todo.end
                todo = editTodo!!
            }

            val resultIntent = Intent()
            resultIntent.putExtra("todo", todo)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun generateID(length: Int): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') // Define the character pool
        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}