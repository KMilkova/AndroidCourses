package com.example.androidcourses.hw9.view.controllers

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw9AddItemBinding
import com.example.androidcourses.hw9.RetrofitInterface
import com.example.androidcourses.hw9.model.Item
import com.example.androidcourses.hw9.model.Rating
import com.example.androidcourses.hw9.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HW9ThirdTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw9AddItemBinding
    private var myCompositeDisposable: CompositeDisposable? = null

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
            Constants.DESCRIPTION,
            0,
            Constants.IMAGE_URL,
            price,
            rating,
            title
        )
        val retrofit = Constants.initRetrofit()
        val service = retrofit.create(RetrofitInterface::class.java)
        myCompositeDisposable = CompositeDisposable()

        val disposable = service.saveItem(item).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Toast.makeText(
                        this@HW9ThirdTaskActivity,
                        Constants.DATA_ADD_MESSAGE,
                        Toast.LENGTH_SHORT
                    )
                        .show()

                    binding.idLoadingPB.visibility = View.GONE
                    binding.category.setText("")
                    binding.price.setText("")
                    binding.title.setText("")
                    binding.rating.setText("")

                    binding.result.text = printRespondResult(it)
                },
                {
                    binding.result.text = Constants.ERROR_MESSAGE
                }
            )


        myCompositeDisposable?.add(disposable)
    }

    private fun printRespondResult(responseItem: Item): String {
        return Constants.RESPONSE_CODE + responseItem +
                Constants.TITLE + responseItem.title +
                Constants.CATEGORY + responseItem.category +
                Constants.PRICE + responseItem.price
    }

}