package com.example.androidcourses

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.HW8DatabaseHelper.Companion.COLUMN_AGE
import com.example.androidcourses.HW8DatabaseHelper.Companion.COLUMN_BIRTHDAY
import com.example.androidcourses.HW8DatabaseHelper.Companion.COLUMN_ID
import com.example.androidcourses.HW8DatabaseHelper.Companion.COLUMN_NAME
import com.example.androidcourses.HW8DatabaseHelper.Companion.COLUMN_PHONE
import com.example.androidcourses.HW8DatabaseHelper.Companion.COLUMN_SURNAME
import com.example.androidcourses.databinding.Hw6FirstTaskActivityBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.util.*


@RequiresApi(Build.VERSION_CODES.N)
class HW6FirstTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw6FirstTaskActivityBinding
    private val personList: MutableList<HW6PersonList> = mutableListOf()
    private val personListAdapter = HW6PersonListAdapter(personList, this)
    private lateinit var sqlDb: SQLiteDatabase
    private lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw6FirstTaskActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = HW8DatabaseHelper(this)
        sqlDb = database.readableDatabase
        cursor = sqlDb.rawQuery("SELECT * FROM ${HW8DatabaseHelper.TABLE_NAME}", null)
        readInfoFormDatabase(cursor)

        binding.writeButton.setOnClickListener {
            writeInfoToDatabase()
            cursor = sqlDb.rawQuery("SELECT * FROM ${HW8DatabaseHelper.TABLE_NAME}", null)
            readInfoFormDatabase(cursor)
        }

        binding.dateEditText.setOnClickListener {
            showDatePickerDialog()
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
        val currentDate = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(cal)
        binding.dateEditText.text = currentDate

        buttonAccessibility(editTexts)

        binding.orderByName.setOnClickListener {
            orderBySomething("name", personList)
        }

        binding.clearOrder.setOnClickListener {
            orderBySomething("", personList)
        }

        binding.orderByAge.setOnClickListener {
            orderBySomething("age", personList)
        }

        binding.showFiveElements.setOnClickListener {
            if (personList.size < 6) {
                Toast.makeText(this, "List size less than 6", Toast.LENGTH_SHORT).show()
            } else {
                takeFiveElements()
            }
        }

    }

    private fun updatePersonListAdapter(list: MutableList<HW6PersonList>) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.personList.layoutManager = layoutManager
        binding.personList.adapter = HW6PersonListAdapter(list, this)
    }

    private fun takeFiveElements() {
        val list = personList.take(5) as MutableList<HW6PersonList>
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.personList.layoutManager = layoutManager
        binding.personList.adapter = HW6PersonListAdapter(list, this)
    }

    private fun orderBySomething(condition: String, list: MutableList<HW6PersonList>) {
        when (condition) {
            "name" -> {
                list.sortBy { it.name }
            }
            "age" -> {
                list.sortBy { it.age }
            }
            else -> {
                list.sortBy { it.id }
            }
        }
        updatePersonListAdapter(list)
    }

    private fun readInfoFormDatabase(cursor: Cursor) {
        personListAdapter.clearInfo(personList)
        with(cursor) {
            if (moveToFirst()) {
                do {
                    personListAdapter.addItems(
                        HW6PersonList(
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SURNAME)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)),
                            SimpleDateFormat(DATE_FORMAT).parse(
                                cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        COLUMN_BIRTHDAY
                                    )
                                )
                            )
                        )
                    )
                } while (moveToNext())
            }

        }
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.personList.layoutManager = layoutManager
        binding.personList.adapter = personListAdapter
    }

    private fun writeInfoToDatabase() {
        val values = ContentValues()
        values.put(COLUMN_NAME, binding.nameEditText.editText?.text.toString())
        values.put(
            COLUMN_SURNAME,
            binding.surnameEditText.editText?.text.toString()
        )
        values.put(
            COLUMN_PHONE,
            binding.phoneEditText.editText?.text.toString().toInt()
        )
        values.put(
            COLUMN_AGE,
            binding.ageEditText.editText?.text.toString().toInt()
        )
        values.put(COLUMN_BIRTHDAY, binding.dateEditText.text.toString())
        sqlDb.insert(HW8DatabaseHelper.TABLE_NAME, null, values)
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
