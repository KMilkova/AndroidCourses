package com.example.androidcourses

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.SecondTaskBinding

class SecondTaskActivity : AppCompatActivity() {
    lateinit var binding: SecondTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //проверка на ввод только букв
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
                    binding.writeButton.isEnabled = editTexts.all { it.text.isNotBlank() }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }

        //вывод информацию из полей в TextView
        binding.writeButton.setOnClickListener {
            binding.nameTextView.text = binding.nameEditText.text
            binding.surnameTextView.text = binding.surnameEditText.text
            binding.phoneTextView.text = binding.phoneEditText.text
            binding.ageTextView.text = binding.ageEditText.text
            binding.clearButton.isEnabled = true
        }

        //очищение полей
        binding.clearButton.setOnClickListener {
            binding.nameTextView.text = ""
            binding.surnameTextView.text = ""
            binding.phoneTextView.text = ""
            binding.ageTextView.text = ""
            binding.clearButton.isEnabled = false
        }
    }
}