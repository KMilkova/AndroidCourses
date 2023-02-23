package com.example.androidcourses

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw4FirstTaskActivityBinding

class FirstForTaskOneActivity:AppCompatActivity() {
    lateinit var binding:Hw4FirstTaskActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstForTaskOneActivity","On create")
        binding = Hw4FirstTaskActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            val intent = Intent(applicationContext,SecondForTaskOneActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("FirstForTaskOneActivity","On start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FirstForTaskOneActivity","On resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FirstForTaskOneActivity","On pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FirstForTaskOneActivity","On stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FirstForTaskOneActivity","On destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("FirstForTaskOneActivity","On restart")
    }
}