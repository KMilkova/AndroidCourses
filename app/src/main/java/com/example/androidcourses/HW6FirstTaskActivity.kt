package com.example.androidcourses

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw6FirstTaskActivityBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable
import java.util.*

const val PERSON_LIST: String = "person_list"
const val DATE_FORMAT:String = "dd.MM.yyyy"
const val REGEX:String = "[A-Za-zА-Яа-я]"

class HW6FirstTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw6FirstTaskActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw6FirstTaskActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var list = mutableListOf<HW6PersonList>()
//        binding.writeButton.setOnClickListener {
//            list.add(
//                HW6PersonList(
//                    binding.nameEditText.text.toString(),
//                    binding.surnameEditText.text.toString(),
//                    binding.phoneEditText.text.toString().toInt(),
//                    binding.ageEditText.text.toString().toInt(),
//                    SimpleDateFormat("dd.MM.yyyy").parse(
//                        binding.dateEditText.text.toString()
//                    )
//                )
//            )
//            var adapter: ArrayAdapter<HW6PersonList> = ArrayAdapter<HW6PersonList>(this, android.R.layout.simple_list_item_1, list)
//            binding.infoList.adapter = adapter
//        }


        val personList: MutableList<HW6PersonList> = mutableListOf()
        val personListAdapter = HW6PersonListAdapter(personList)
        binding.writeButton.setOnClickListener {
            personListAdapter.addItems(
                HW6PersonList(
                    binding.nameEditText.editText?.text.toString(),
                    binding.surnameEditText.editText?.text.toString(),
                    binding.phoneEditText.editText?.text.toString().toInt(),
                    binding.ageEditText.editText?.text.toString().toInt(),
                    SimpleDateFormat(DATE_FORMAT).parse(binding.dateEditText.text.toString())
                )
            )
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.personList.layoutManager = layoutManager
            binding.personList.adapter = personListAdapter
        }

        binding.dateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        val regex = Regex(REGEX)
        binding.nameEditText.editText?.filters =
            arrayOf(InputFilter { source, _, _, _, _, _ ->
                source.filter {
                    regex.matches(it.toString())
                }
            })
        binding.surnameEditText.editText?.filters =
            arrayOf(InputFilter { source, _, _, _, _, _ ->
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
        val currentDate = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(cal)
        binding.dateEditText.text = currentDate

        buttonAccessibility(editTexts)

        binding.nextScreen.setOnClickListener {
            val list = personListAdapter.getItems()
            val intent = Intent(applicationContext, HW6SecondActivity::class.java)
            intent.putExtra(PERSON_LIST, list as Serializable)
            startActivity(intent)
            list.forEach { println(it.name) }
        }

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
                    binding.nextScreen.isEnabled =
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
                .setTitleText("Select date").build()


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