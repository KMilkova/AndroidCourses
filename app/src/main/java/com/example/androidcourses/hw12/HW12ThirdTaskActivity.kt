package com.example.androidcourses.hw12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw12SecondTaskBinding
import io.reactivex.rxjava3.core.Observable

class HW12ThirdTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw12SecondTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw12SecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.secondTaskBtn.setOnClickListener {
            splitPositiveAndNegative()
            displayWordsWithLengthLessThan()
            skipAllNegativeValuesUntilOnePositiveOneArrives()
            outputAllWordsUntilTheWordStopComes()
        }
    }

    private fun splitPositiveAndNegative() {
        println("split positive and negative: -1, 2, -3, -4, 5, -6, 7, 8, -9")
        Observable.just(-1, 2, -3, -4, 5, -6, 7, 8, -9)
            .groupBy { it < 0 }
            .flatMapSingle {
                it.toList()
            }
            .subscribe {
                println(it)
            }
    }

    private fun displayWordsWithLengthLessThan(){
        println("Display words with length less than 6: pie, sun,nastya, australia, android")
        Observable.just("pie", "sun","nastya", "australia", "android" )
            .takeWhile { it.length < 6
            }
            .subscribe{
                println(it)
            }
    }

    private fun skipAllNegativeValuesUntilOnePositiveOneArrives(){
        println("Skip all negative values until one positive one arrives: -15,-9,-7,5,1,-7")
        Observable.just(-15,-9,-7,5,1,-7)
            .takeUntil{ it > 0 }
            .subscribe{
                println(it)
            }
    }

    private fun outputAllWordsUntilTheWordStopComes(){
        println("output all words until the word stop comes")
        Observable.just("pie", "sun","nastya", "stop", "android")
            .takeUntil{it.lowercase() == "stop"}
            .subscribe{
                println(it)
            }
    }
}