package com.example.androidcourses

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw4SecondTaskBinding

private const val TAG = "TaskTwoActivity"
private const val KEY_RESULT = "result"
private var res = ""

class TaskTwoActivity:AppCompatActivity() {
    lateinit var binding:Hw4SecondTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw4SecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState!=null){
            binding.resultTextView.text = savedInstanceState.getString(KEY_RESULT)
        }

        val editTexts = listOf(
            binding.nameEditText,
            binding.surnameEditText,
            binding.phoneEditText,
            binding.ageEditText
        )

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
            var el: String =
                "${binding.nameEditText.text}, ${binding.surnameEditText.text}, ${binding.phoneEditText.text}, ${binding.ageEditText.text}"
            binding.resultTextView.text = el
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_RESULT, binding.resultTextView.text.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"On onResume")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"On restart")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"On pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"On stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"On destroy")
    }
}