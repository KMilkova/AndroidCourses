package com.example.androidcourses

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.FistTaskBinding

class FistTaskActivity : AppCompatActivity() {
    lateinit var binding: FistTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FistTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //проверка на ввод только букв
        var regex = Regex("[A-Za-zА-Яа-я]]")
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


        //список полей для ввода данных
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

        //вывод информацию из полей в TextView
        binding.button.setOnClickListener {
            var el: String =
                "${binding.nameEditText.text} \n ${binding.surnameEditText.text} \n ${binding.phoneEditText.text} \n ${binding.ageEditText.text}"
            binding.resultTextView.text = el
        }

    }

}