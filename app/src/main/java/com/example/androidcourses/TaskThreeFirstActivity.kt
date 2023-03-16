package com.example.androidcourses

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw4ThirdTaskBinding

const val NAME:String = "Name"
const val SURNAME:String = "Surname"
const val PHONE:String = "Phone"
const val AGE:String = "Age"
const val GENDER:String = "Gender"
const val EMAIL:String = "Email"
const val BIRTHDAY:String = "BIRTHDAY"

class TaskThreeFirstActivity: AppCompatActivity() {
    lateinit var binding: Hw4ThirdTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw4ThirdTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var regex = Regex("[A-Za-zА-Яа-я]")
        binding.nameEditText.filters =
            arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
                source.filter {
                    regex.matches(it.toString())
                }
            })
        binding.surnameEditText.filters =
            arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
                source.filter {
                    regex.matches(it.toString())
                }
            })

        val editTexts = listOf(
            binding.nameEditText,
            binding.surnameEditText,
            binding.phoneEditText,
            binding.ageEditText
        )

        //проверка на заполненность полей, для того, чтобы кнопка стала доступной
        for (editText in editTexts) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.button.isEnabled = editTexts.all { it.text.isNotBlank() }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }

        binding.button.setOnClickListener {
            val intent = Intent(applicationContext,TaskThreeSecondActivity::class.java)
            intent.putExtra(NAME, binding.nameEditText.text.toString())
            intent.putExtra(SURNAME, binding.surnameEditText.text.toString())
            intent.putExtra(PHONE, binding.phoneEditText.text.toString().toInt())
            intent.putExtra(AGE, binding.ageEditText.text.toString().toInt())
            startActivity(intent)
        }

    }
}