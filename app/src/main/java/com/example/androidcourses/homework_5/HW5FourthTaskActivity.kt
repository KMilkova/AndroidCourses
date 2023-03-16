package com.example.androidcourses.homework_5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.R
import com.example.androidcourses.databinding.Hw5FourthTaskBinding
import com.example.androidcourses.fragments.HW5FourthTaskFirstFragment
import com.example.androidcourses.fragments.HW5FourthTaskSecondFragment

class HW5FourthTaskActivity:AppCompatActivity() {
    lateinit var binding: Hw5FourthTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw5FourthTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var supportManager = supportFragmentManager

        //вывод первого фрагмента
        binding.buttonForFirstFragment.setOnClickListener {
            supportManager.beginTransaction().replace(R.id.frameLayoutForFragmentsWithPictures, HW5FourthTaskFirstFragment()).commit()
        }

        //вывод второго фрагмента
        binding.buttonForSecondFragment.setOnClickListener {
            supportManager.beginTransaction().replace(R.id.frameLayoutForFragmentsWithPictures, HW5FourthTaskSecondFragment()).commit()
        }
    }
}