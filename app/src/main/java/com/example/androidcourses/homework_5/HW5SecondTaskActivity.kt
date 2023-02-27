package com.example.androidcourses.homework_5

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.R
import com.example.androidcourses.databinding.Hw5SecondTaskBinding
import com.example.androidcourses.fragments.HW5SecondTaskFragment
import com.example.androidcourses.fragments.HW5ThirdTaskFragment

class HW5SecondTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw5SecondTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw5SecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //работа с фрагментами 2 и 3 задания
        val supportManager = supportFragmentManager
        supportManager.beginTransaction()
            .add(R.id.frameLayoutMain, HW5SecondTaskFragment())
            .commit()

        supportManager.beginTransaction()
            .add(R.id.frameLayoutForTaskThree, HW5ThirdTaskFragment())
            .commit()

        //нажатие кнопки в активити для передачи данных в первый фрагмент (2 задание)
        binding.button.setOnClickListener {
            supportManager.beginTransaction()
                .replace(
                    R.id.frameLayoutMain,
                    HW5SecondTaskFragment.newInstance(
                        binding.nameEditText.text.toString(),
                        binding.ageEditText.text.toString().toInt(),
                        binding.weightEditText.text.toString().toDouble()
                    )
                )
                .commit()

            supportManager.beginTransaction()
                .add(R.id.frameLayoutForTaskThree, HW5ThirdTaskFragment())
                .commit()
        }


        //получение данных из первого фрагмента
        supportManager.setFragmentResultListener("requestKey", this) { requestKey, bundle ->
            val name: String? = bundle.getString("nameKey")
            val age = bundle.getInt("ageKey")
            val weight = bundle.getDouble("weightKey")
            val check = bundle.getBoolean("checkClickKey")

            //если кнопка во фрагменте была нажата, во второй фрагмент посылаются данные
            if (check) sendInfoToThirdTaskFragment(name, age, weight)

        }

        val editTexts = listOf(
            binding.nameEditText,
            binding.ageEditText,
            binding.weightEditText
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

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    binding.button.isEnabled = editTexts.all { it.text.isNotBlank() }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }

    }
    //отправка данных во второй фрагмент (3 задание)
    private fun sendInfoToThirdTaskFragment(name: String?, age: Int, weight: Double) {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayoutForTaskThree, HW5ThirdTaskFragment.newInstance(
                name,
                age,
                weight
            )
        )
            .commit()
    }
}