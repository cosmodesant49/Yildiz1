package com.geeks.yildiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geeks.yildiz.R
import com.geeks.yildiz.databinding.ActivityGroupBinding
import com.geeks.yildiz.databinding.ActivityLoginBinding

class GroupActivity : AppCompatActivity() {

    lateinit var binding: ActivityGroupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGroup1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}