package com.example.androidcourses.hw12

import io.reactivex.rxjava3.core.Observable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw12SecondTaskBinding

class HW12SecondTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw12SecondTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw12SecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.secondTaskBtn.setOnClickListener {

            val observable1 = Observable.just(1, 2, 3, 5, 7)
            val observable2 = Observable.just(4, 6, 8, 10, 12)

            println("Observable1: 1, 2, 3, 5, 7")
            println("Observable2: 4, 6, 8, 10, 12")

            println("combineLatest")
            val combineLatest =
                Observable.combineLatest(observable1, observable2) { x, y -> x + y }
                    .subscribe {
                        println(it)
                    }
            println("withLatestFrom")
            val withLatestFrom = observable1.withLatestFrom(observable2) { x, y -> x + y }
                .subscribe {
                    println(it)
                }
            println("merge")
            val merge = Observable.merge(observable1, observable2)
                .subscribe {
                    println(it)
                }
            println("concat")
            val concat = Observable.concat(observable1, observable2)
                .subscribe {
                    println(it)
                }
            println("Zip")
            val zip = Observable.zip(observable1, observable2) { x, y -> x + y }
                .subscribe {
                    println(it)
                }

        }
    }
}