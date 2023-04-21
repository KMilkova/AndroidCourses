package com.example.androidcourses.hw9

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw9AddItemBinding
import com.example.androidcourses.hw9.shop.Item
import com.example.androidcourses.hw9.shop.Rating
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HW9ThirdTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw9AddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw9AddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addItem.setOnClickListener {
            postData(
                binding.category.text.toString(),
                binding.price.text.toString().toDouble(),
                Rating(4, binding.rating.text.toString().toDouble()),
                binding.title.text.toString()
            )
        }

        val editTexts = listOf(
            binding.category,
            binding.title,
            binding.price,
            binding.rating
        )

        buttonAccessibility(editTexts)


    }


    private fun buttonAccessibility(listOfFields: List<EditText>) {
        for (editText in listOfFields) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.addItem.isEnabled =
                        listOfFields.all { it.text.toString().isNotBlank() }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun postData(
        category: String,
        price: Double,
        rating: Rating,
        title: String
    ) {

        binding.idLoadingPB.visibility = View.VISIBLE

        val item = Item(
            category,
            DESCRIPTION,
            0,
            IMAGE_URL,
            price,
            rating,
            title
        )
        val retrofit = HW9FirsTaskActivity.initRetrofit()
        val service = retrofit.create(RetrofitInterface::class.java)
        val call = service.saveItem(item)

        call.enqueue(object : Callback<Item> {

            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                Toast.makeText(this@HW9ThirdTaskActivity, DATA_ADD_MESSAGE, Toast.LENGTH_SHORT)
                    .show()

                binding.idLoadingPB.visibility = View.GONE
                binding.category.setText("")
                binding.price.setText("")
                binding.title.setText("")
                binding.rating.setText("")

                binding.result.text = printRespondResult(response)
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                binding.result.text = HW9FirsTaskActivity.ERROR_MESSAGE + t.message
            }
        })
    }

    fun printRespondResult(responseItem: Response<Item>): String {
        return RESPONSE_CODE + responseItem.code() +
                TITLE + responseItem.body()?.title +
                CATEGORY + responseItem.body()?.category +
                PRICE + responseItem.body()?.price
    }

    companion object {
        const val DATA_ADD_MESSAGE = "Data added to API"
        const val RESPONSE_CODE = "Response Code : "
        const val TITLE = "\ntitle : "
        const val CATEGORY = "\ncategory : "
        const val PRICE = "\nprice : "
        const val DESCRIPTION = "smth"
        const val IMAGE_URL = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
    }
}