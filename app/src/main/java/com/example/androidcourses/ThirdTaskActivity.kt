package com.example.androidcourses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.ThirdTaskBinding

class ThirdTaskActivity:AppCompatActivity() {
    lateinit var binding: ThirdTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ThirdTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}