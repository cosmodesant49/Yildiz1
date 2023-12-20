package com.geeks.yildiz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geeks.yildiz.R
import com.geeks.yildiz.adapter.QuizAdapter
import com.geeks.yildiz.databinding.ActivityMainBinding
import com.geeks.yildiz.model.Quiz
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects

class MainActivity : AppCompatActivity() {

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    lateinit var adapeter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        populateDummyData()
        setUpViews()

    }

    private fun populateDummyData() {
        quizList.add(Quiz("16-12-2023", "16-12-2023"))
    }

    fun setUpViews() {
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecyclerView()
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference: CollectionReference = firestore.collection("quizzers")
        collectionReference.addSnapshotListener{value, error ->
            if (value == null || error == null){
                Toast.makeText(this,"Data error", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapeter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        adapeter = QuizAdapter(this, quizList)
        binding.quizRecyclerView.layoutManager = GridLayoutManager(this, 1)
        binding.quizRecyclerView.adapter = adapeter
    }

    fun setUpDrawerLayout() {
        setSupportActionBar(binding.appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawer,
            R.string.app_name,
            R.string.app_name
        )
        binding.mainDrawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
