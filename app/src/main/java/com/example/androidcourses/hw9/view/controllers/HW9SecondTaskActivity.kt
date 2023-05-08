package com.example.androidcourses.hw9.view.controllers

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.androidcourses.databinding.Hw9ItemInfoBinding
import com.example.androidcourses.hw9.RetrofitInterface
import com.example.androidcourses.hw9.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HW9SecondTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw9ItemInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw9ItemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared = this.getSharedPreferences(
            Constants.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val id = shared.getInt(Constants.ITEM_ID, 0)

        val retrofit = Constants.initRetrofit()
        val service = retrofit.create(RetrofitInterface::class.java)
        val call = service.getItem(id)
        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    with(binding) {
                        title.text = it.title
                        category.text = it.category
                        price.text = it.price.toString()
                        rating.text = it.rating.rate.toString()
                        Glide.with(applicationContext)
                            .load(it.image)
                            .into(binding.picture)

                    }
                },
                {
                    Toast.makeText(
                        this@HW9SecondTaskActivity,
                        Constants.ERROR_MESSAGE,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            )

    }


}

