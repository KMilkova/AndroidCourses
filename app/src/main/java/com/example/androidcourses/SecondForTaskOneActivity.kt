package com.example.androidcourses

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw4FirstTaskSecondActivityBinding

class SecondForTaskOneActivity:AppCompatActivity() {
    lateinit var binding:Hw4FirstTaskSecondActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondForTaskOneActivity","On create")
        binding = Hw4FirstTaskSecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondForTaskOneActivity","On start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondForTaskOneActivity","On resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondForTaskOneActivity","On pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondForTaskOneActivity","On stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondForTaskOneActivity","On destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondForTaskOneActivity","On restart")
    }
}