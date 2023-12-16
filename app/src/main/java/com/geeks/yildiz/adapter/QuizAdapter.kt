package com.geeks.yildiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.geeks.yildiz.R
import com.geeks.yildiz.databinding.QuizItemBinding
import com.geeks.yildiz.model.Quiz

class QuizAdapter(val context: Context, val quizList: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return quizList.size    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizList[position].title
    }

    inner class QuizViewHolder(private val binding: QuizItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            /*var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
            var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
            var cardContainer: CardView = itemView.findViewById(R.id.quizCardContainer)
*/
            val textViewTitle = binding.quizTitle
            val iconView = binding.quizIcon
            val cardContainer = binding.quizCardContainer


    }
}
