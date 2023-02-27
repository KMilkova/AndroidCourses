package com.example.androidcourses.homework_5

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.R
import com.example.androidcourses.databinding.Hw5FirstTaskBinding
import com.example.androidcourses.fragments.HW5FirstTaskFragment

private const val TAG = "HW5FirstTaskActivity"

class HW5FirstTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw5FirstTaskBinding

    //Задание 1 активити и её жц
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw5FirstTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //фрагмент
        var supportFragmentManager = supportFragmentManager.beginTransaction()
        supportFragmentManager.replace(R.id.frameLayout, HW5FirstTaskFragment()).commit()
        Log.d(TAG,"On create")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"On start")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"On resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"On pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"On stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"On destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"On restart")
    }
}