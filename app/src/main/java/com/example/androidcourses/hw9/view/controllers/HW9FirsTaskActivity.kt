package com.example.androidcourses.hw9.view.controllers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw9MainActivityBinding
import com.example.androidcourses.hw9.RetrofitInterface
import com.example.androidcourses.hw9.utils.Constants
import com.example.androidcourses.hw9.view.adapters.HW9ItemsAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HW9FirsTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw9MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw9MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit = Constants.initRetrofit()
        val service = retrofit.create(RetrofitInterface::class.java)

        service.getItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val items = it
                    val itemsAdapter = HW9ItemsAdapter(items, applicationContext)
                    val layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    binding.items.layoutManager = layoutManager
                    binding.items.adapter = itemsAdapter
                },
                {
                    println("Error")
                }
            )


        binding.addItem.setOnClickListener {
            val intent = Intent(this, HW9ThirdTaskActivity::class.java)
            startActivity(intent)
        }
    }



}