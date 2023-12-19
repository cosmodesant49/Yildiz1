package com.geeks.yildiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.geeks.yildiz.R
import com.geeks.yildiz.model.Questions

class OptionAdapter(val context: Context, val questions: Questions?) :
    RecyclerView.Adapter<OptionAdapter.OptionalViewHolder>() {


    private var options: List<String> = questions?.let {
        listOf(it.option1, it.option2, it.option3, it.option4)
    } ?: emptyList()


    inner class OptionalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var optionalView = itemView.findViewById<TextView>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionalViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.optional_item, parent, false)
        return OptionalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: OptionalViewHolder, position: Int) {
        holder.optionalView.text = options[position]
        holder.itemView.setOnClickListener {
            questions?.userAnswer = options[position]
            notifyDataSetChanged()
        }

        if (questions?.userAnswer == options[position]) {
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }


    }
}