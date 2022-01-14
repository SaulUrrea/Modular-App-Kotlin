package com.example.task

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.CardItemBinding

class TaskAdapter(private val tasks: MutableList<TaskModel>, private val listener: OnClickListener) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val task = tasks.get(position)
        with(holder){
            setListener(task)
            if (task.guia != null) {
                binding.tvTitle.text = "¡Nueva entrega!"
                binding.tvAdTitle.text = task.ciudad
                binding.tvSubtitle1.text = task.guia
                binding.tvSubtitle2.text = "Unidades: ${task.unidades}"
            }
            if (task.solicitante != null){
                binding.tvTitle.text = "¡Nueva recogida!"
                binding.tvAdTitle.text = task.ciudad
                binding.tvSubtitle1.text = task.direccion
                binding.tvSubtitle2.text = "Unidades: ${task.unidades}"
                binding.iconTask.setImageResource(R.drawable.ic_outline_local_shipping_24)
            }
        }

    }

    override fun getItemCount(): Int = tasks.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = CardItemBinding.bind(view)

        fun setListener(task: TaskModel){
            binding.root.setOnClickListener {
                listener.onClick(task)
            }
        }

    }
}