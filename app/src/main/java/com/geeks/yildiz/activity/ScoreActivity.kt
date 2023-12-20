package com.geeks.yildiz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geeks.yildiz.R
import com.geeks.yildiz.databinding.ActivityQuestionBinding
import com.geeks.yildiz.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvScore.text = intent.getStringExtra("key")
    }
}