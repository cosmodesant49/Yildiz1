package com.geeks.yildiz.activity

import android.content.Intent
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
    private var questions: MutableList<Questions> = mutableListOf(
        Questions(
            "Merhaba",
            "Привет",
            "Пока",
            "Мама",
            "Папа",
            "Привет"

        ),
        Questions(
            "Evet",
            "Да",
            "Нет",
            "Не знаю",
            "Пока",
            "Да"
        ),
        Questions(
            "Hayır",
            "Нет",
            "Привет",
            "Пока",
            "Да",
            "Нет"
        ),
        Questions(
            "Lütfen",
            "Пожалуйста",
            "Спасибо",
            "Как дела?",
            "Как тебя зовут",
            "Пожалуйста"
        ),
        Questions(
            "Teşekkür ederim",
            "Пожалуйста",
            "Спасибо",
            "Как дела?",
            "Как тебя зовут",
            "Спасибо"
        ),
    )
    private var index = 1

    /*Создать переменную для хранения результата*/
    private var result = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
        /*setUpEventLisiner(questions.get(index-1))*/

    }

    private fun setUpEventLisiner(question: Questions) {
        binding.btnPrevious.setOnClickListener {
            index--
            bindViews()
        }
        binding.btnNext.setOnClickListener {
            if (question.userAnswer.equals(question.answer)) {
                result += 20
                Log.e("ololo", "${question.userAnswer}")
            }

            index++
            bindViews()
        }
        binding.btnSubmit.setOnClickListener {
            if (question.userAnswer.equals(question.answer)) {
                result += 20

                Log.e("ololo", "${question.userAnswer}")
            }


            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("key", result.toString())
            startActivity(intent)
        }
    }

    private fun bindViews() {
        binding.btnPrevious.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE
        binding.btnNext.visibility = View.GONE

        if (index == 1) {
            binding.btnNext.visibility = View.VISIBLE
        } else if (index == questions.size) {
            binding.btnSubmit.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.VISIBLE
        } else {
            binding.btnPrevious.visibility = View.VISIBLE
            binding.btnNext.visibility = View.VISIBLE
        }

        //  val questions: Questions = questions[index - 1]

        val question: Questions = questions[index - 1].copy()
        binding.description.text = question.description

        val optionAdapter = OptionAdapter(this, question)
        binding.optionalList.adapter = optionAdapter

        setUpEventLisiner(question)
        //binding.optionalList.setHasFixedSize(true)
    }
}