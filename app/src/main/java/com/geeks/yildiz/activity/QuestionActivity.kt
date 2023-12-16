package com.geeks.yildiz.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.yildiz.adapter.OptionAdapter
import com.geeks.yildiz.databinding.ActivityQuestionBinding
import com.geeks.yildiz.model.Questions

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindViews()
    }

    private fun bindViews() {
        val questions = Questions(
            "Merhaba",
            "Привет",
            "Пока",
            "Мама",
            "Папа",
            "Привет"
        )

        binding.description.text = questions.description

        val optionAdapter = OptionAdapter(this, questions)
        binding.optionalList.layoutManager = LinearLayoutManager(this)
        binding.optionalList.adapter = optionAdapter
        binding.optionalList.setHasFixedSize(true)
    }
}
