package com.example.tutorial05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial05.adapters.TodoAdapter
import com.example.tutorial05.database.TodoDatabase
import com.example.tutorial05.database.entities.Todo
import com.example.tutorial05.database.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = TodoRepository(TodoDatabase.getInstance(this))
        val recyclerView:RecyclerView = findViewById(R.id.rvToDoList)
        val ui = this
        val adapter = TodoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val btnAddTodo = findViewById<Button>(R.id.btnAddToDo)

        btnAddTodo.setOnClickListener{
            DisplayDialog(repository, adapter)
        }

        CoroutineScope(Dispatchers.IO).launch{
            val data = repository.getAllTodos()
            adapter.setData(data,ui)
        }
    }
    fun DisplayDialog(repository: TodoRepository, adapter: TodoAdapter){
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Enter new todo item : ")
        builder.setMessage("Enter the todo item below : ")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("OK"){
            dialog, which -> val item = input.text.toString()
            CoroutineScope (Dispatchers.IO).launch{
                repository.insert(Todo(item))
                val data = repository.getAllTodos()
                runOnUiThread{
                    adapter.setData(data,this@MainActivity)
                }
            }
        }
        //set negative button
        builder.setNegativeButton("Cancel"){
            dialog, which -> dialog.cancel()
        }
    }
}