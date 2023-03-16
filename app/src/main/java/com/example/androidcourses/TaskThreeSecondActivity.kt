package com.example.androidcourses

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw4ThirdTaskSecondActivityBinding
import java.text.SimpleDateFormat
import java.util.*

class TaskThreeSecondActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var binding: Hw4ThirdTaskSecondActivityBinding

    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("dd.MM.yyyy")
    private var checkDate: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw4ThirdTaskSecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name: String? = intent.getStringExtra(NAME)
        val surname: String? = intent.getStringExtra(SURNAME)
        val phone: Int = intent.getIntExtra(PHONE, 0)
        val age: Int = intent.getIntExtra(AGE, 0)

        var isFemale: Boolean = true
        var check: Boolean = false

        val currentDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        binding.dateTextView.text = currentDate

        binding.wGenderRadioButton.isChecked = true

        var radioGrp: RadioGroup = binding.genderRadioGroup
        radioGrp.setOnCheckedChangeListener { group, checkedId ->
            var rb: RadioButton = findViewById<RadioButton>(checkedId)
            Log.d("checked", "${rb.text}")
            check = true

            isFemale = checkedId == binding.wGenderRadioButton.id
        }
        binding.dateTextView.setOnClickListener {
            var datePickerDialog: DatePickerDialog = DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            var datePicker = datePickerDialog.datePicker
            var c = Calendar.getInstance()
            datePicker.maxDate = c.timeInMillis
            datePickerDialog.show()
        }

        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.button.isEnabled = binding.emailEditText.text.isNotBlank()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.button.setOnClickListener {
            if (validateEmail(binding.emailEditText)) {
                var intent = Intent(applicationContext, TaskThreeThirdActivity::class.java)
                intent.putExtra(NAME, name)
                intent.putExtra(SURNAME, surname)
                intent.putExtra(PHONE, phone)
                intent.putExtra(AGE, age)
                intent.putExtra(GENDER, isFemale)
                intent.putExtra(EMAIL, binding.emailEditText.text.toString())
                intent.putExtra(BIRTHDAY, binding.dateTextView.text.toString())
                startActivity(intent)
            }
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        displayFormattedDate(calendar.timeInMillis)
    }

    private fun displayFormattedDate(timestamp: Long) {
        binding.dateTextView.text = formatter.format(timestamp)
    }

    private fun validateEmail(email: EditText): Boolean {
        var emailInput: String = email.text.toString()
        return if (emailInput.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(this, "Email valid", Toast.LENGTH_SHORT).show()
            true
        } else {
            Toast.makeText(this, "Enter valid Email address", Toast.LENGTH_SHORT).show();
            false
        }
    }
}