package com.example.androidcourses.hw10.view.activities

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.*
import android.text.InputFilter
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.androidcourses.databinding.Hw8ThirdTaskAddPersonsInfoBinding
import com.example.androidcourses.hw10.model.entity.Person
import com.example.androidcourses.hw10.utils.Constants.Companion.DATE_FORMAT
import com.example.androidcourses.hw10.utils.Constants.Companion.REGEX
import com.example.androidcourses.hw10.utils.Constants.Companion.TITLE_FOR_DATE_PICKER
import com.example.androidcourses.hw10.view.adapters.HW10MainActivityAdapter
import com.example.androidcourses.hw10.viewModel.HW10MainActivityViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
class HW10MainActivity : AppCompatActivity() {
    private lateinit var binding: Hw8ThirdTaskAddPersonsInfoBinding
    private lateinit var mainActivityViewModel: HW10MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8ThirdTaskAddPersonsInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        mainActivityViewModel =
            ViewModelProvider(this)[HW10MainActivityViewModel::class.java]

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.personList.layoutManager = layoutManager
        val adapter = HW10MainActivityAdapter(
            mutableListOf(), mainActivityViewModel
        )
        binding.personList.adapter = adapter


        mainActivityViewModel.personList.observe(this) {
            adapter.updateList(it)
        }

        binding.writeButton.setOnClickListener {
            mainActivityViewModel.insertElToDB(
                Person(
                    0,
                    binding.nameEditText.editText?.text.toString(),
                    binding.surnameEditText.editText?.text.toString(),
                    binding.phoneEditText.editText?.text.toString().toInt(),
                    binding.ageEditText.editText?.text.toString().toInt(),
                    binding.dateEditText.text.toString(),
                )
            )
        }

        binding.ShowAddresses.setOnClickListener {
            val intent = Intent(this, HW10AddressInfoActivity::class.java)
            startActivity(intent)
        }

        val regex = Regex(REGEX)
        binding.nameEditText.editText?.filters =
            arrayOf(InputFilter
            { source, _, _, _, _, _ ->
                source.filter {
                    regex.matches(it.toString())
                }
            })

        binding.surnameEditText.editText?.filters =
            arrayOf(InputFilter
            { source, _, _, _, _, _ ->
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
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val currentDate =
            SimpleDateFormat(HW6FirstTaskActivity.DATE_FORMAT, Locale.getDefault()).format(cal)
        binding.dateEditText.text = currentDate

        buttonAccessibility(editTexts)
    }

    private fun buttonAccessibility(listOfFields: List<TextInputLayout>) {
        for (editText in listOfFields) {
            editText.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.writeButton.isEnabled =
                        listOfFields.all { it.editText?.text.toString().isNotBlank() }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun showDatePickerDialog() {
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val constraint = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.before(today))
        val birthday =
            MaterialDatePicker.Builder.datePicker().setCalendarConstraints(constraint.build())
                .setTheme(R.style.CustomThemeOverlay_MaterialCalendar_Fullscreen)
                .setTitleText(TITLE_FOR_DATE_PICKER).build()


        birthday.addOnPositiveButtonClickListener {
            val cal = Calendar.getInstance()
            cal.timeInMillis = it
            val format = SimpleDateFormat(DATE_FORMAT)
            val formatted: String = format.format(cal.time)
            binding.dateEditText.text =
                formatted
        }
        birthday.show(supportFragmentManager, "MATERIAL_DATE_PICKER")

    }


}