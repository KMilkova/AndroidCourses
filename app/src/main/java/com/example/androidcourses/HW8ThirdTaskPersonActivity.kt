package com.example.androidcourses

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw8ThirdTaskAddPersonsInfoBinding
import com.example.androidcourses.room.AppDatabase
import com.example.androidcourses.room.entity.Person
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import java.util.concurrent.Executors

@RequiresApi(Build.VERSION_CODES.N)
class HW8ThirdTaskPersonActivity : AppCompatActivity() {
    lateinit var binding: Hw8ThirdTaskAddPersonsInfoBinding
    var personList: MutableList<Person> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8ThirdTaskAddPersonsInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db: AppDatabase = AppDatabase.getInstance(this)

        HW8ThirdTaskPersonInfoAdapter(personList, db, this)
        binding.dateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val personDao = db.personDao()
        Executors.newSingleThreadExecutor().execute {
            personList = personDao.getAll().toMutableList()
            binding.personList.layoutManager = layoutManager
            binding.personList.adapter = HW8ThirdTaskPersonInfoAdapter(personList, db, this)

        }

        binding.writeButton.setOnClickListener {
            Executors.newSingleThreadExecutor().execute {

                personDao.insertPerson(
                    Person(
                        0,
                        binding.nameEditText.editText?.text.toString(),
                        binding.surnameEditText.editText?.text.toString(),
                        binding.phoneEditText.editText?.text.toString().toInt(),
                        binding.ageEditText.editText?.text.toString().toInt(),
                        binding.dateEditText.text.toString(),
                    )
                )

                personList.clear()
                personList = personDao.getAll().toMutableList()
                runOnUiThread {
                    binding.personList.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.personList.adapter = HW8ThirdTaskPersonInfoAdapter(personList, db, this)
                }

            }

        }

        binding.ShowAddresses.setOnClickListener {
            val intent = Intent(this, HW8ThirdTaskAddressActivity::class.java)
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

    companion object {
        const val PERSON_LIST: String = "person_list"
        const val DATE_FORMAT: String = "dd.MM.yyyy"
        const val REGEX: String = "[A-Za-zА-Яа-я]"
        const val TITLE_FOR_DATE_PICKER = "Select date"
    }
}