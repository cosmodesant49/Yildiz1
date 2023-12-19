package com.geeks.yildiz.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.yildiz.adapter.OptionAdapter
import com.geeks.yildiz.databinding.ActivityQuestionBinding
import com.geeks.yildiz.model.Questions
import com.geeks.yildiz.model.Quiz
import com.google.firebase.firestore.FirebaseFirestore

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    var quizzes : MutableList<Quiz>?=null
    var questions : MutableMap<String, Questions>?=null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpFirestore()
    }

    private fun setUpFirestore() {
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

        // Выполните запрос и обработайте результат
        firestore.collection("quizzes").get().addOnSuccessListener {
            if ( it != null && !it.isEmpty) {
                Log.d("DATA", it.toObjects(Quiz::class.java).toString())
                quizzes = it.toObjects(Quiz::class.java)
                questions = quizzes!![0].questions
                bindViews()
            }
        }
    }

    private fun bindViews() {

        binding.btnPrevious.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE
        binding.btnNext.visibility = View.GONE

        if (index == 1) {
            binding.btnNext.visibility = View.VISIBLE
        } else if (index == questions!!.size) {
            binding.btnSubmit.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.VISIBLE
        } else {
            binding.btnPrevious.visibility = View.VISIBLE
            binding.btnNext.visibility = View.VISIBLE
        }


/*        val questions = Questions(
            "Merhaba",
            "Привет",
            "Пока",
            "Мама",
            "Папа",
            "Привет"
        )*/
        val questions: Questions? = questions!!["question$index"]
        questions?.let {

            binding.description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            binding.optionalList.layoutManager = LinearLayoutManager(this)
            binding.optionalList.adapter = optionAdapter
            binding.optionalList.setHasFixedSize(true)
        }
    }
}
