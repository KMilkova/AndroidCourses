package com.example.androidcourses.hw9

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw9MainActivityBinding
import com.example.androidcourses.hw9.shop.Item
import com.google.android.material.textfield.TextInputLayout
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HW9FirsTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw9MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw9MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit = initRetrofit()
        val service = retrofit.create(RetrofitInterface::class.java)

        service.getItems().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                val items = response.body()!!
                val itemsAdapter = HW9ItemsAdapter(items, applicationContext)
                val layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.items.layoutManager = layoutManager
                binding.items.adapter = itemsAdapter
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                println(t.message)
            }
        })


        binding.addItem.setOnClickListener {
            val intent = Intent(this, HW9ThirdTaskActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val BASE_URL = "https://fakestoreapi.com/"
        const val SHARED_PREFERENCES_NAME = "item_information"
        const val ITEM_ID = "id"
        const val ERROR_MESSAGE = "Error found is : "
        fun initRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


}