package com.geeks.yildiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.geeks.yildiz.databinding.ActivityLogIntoBinding
import com.google.firebase.auth.FirebaseAuth

class LogIntoActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogIntoBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogIntoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            Toast.makeText(this, "User is already logged in" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        binding.btnGetGtarted.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }
}