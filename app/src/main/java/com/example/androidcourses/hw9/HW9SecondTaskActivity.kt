package com.example.androidcourses.hw9

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.androidcourses.databinding.Hw9ItemInfoBinding
import com.example.androidcourses.hw9.shop.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HW9SecondTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw9ItemInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw9ItemInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared = this.getSharedPreferences(
            HW9FirsTaskActivity.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val id = shared.getInt(HW9FirsTaskActivity.ITEM_ID, 0)

        val retrofit = HW9FirsTaskActivity.initRetrofit()
        val service = retrofit.create(RetrofitInterface::class.java)
        val call = service.getItem(id)
        call.enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                val persons = response.body()
                if (response.isSuccessful) {
                    with(binding) {
                        title.text = persons?.title
                        category.text = persons?.category
                        price.text = persons?.price.toString()
                        rating.text = persons?.rating?.rate.toString()
                        Glide.with(applicationContext)
                            .load(persons?.image)
                            .into(binding.picture)

                    }
                }
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                Toast.makeText(
                    this@HW9SecondTaskActivity,
                    HW9FirsTaskActivity.ERROR_MESSAGE,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })

    }

}

