package com.example.tutorial05.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tutorial05.R
import com.example.tutorial05.database.entities.Todo

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    lateinit var data:List<Todo>
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cbTodo : CheckBox
        val ivDelete:ImageView

        init {
            cbTodo = view.findViewById(R.id.cbTodo)
            ivDelete = view.findViewById(R.id.ivDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
    return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.cbTodo.text = "Sample text"

    }

}