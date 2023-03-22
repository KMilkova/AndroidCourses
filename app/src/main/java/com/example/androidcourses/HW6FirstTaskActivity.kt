package com.example.androidcourses

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.HW6SecondActivity.Companion.POSITIVE_ANSWER
import com.example.androidcourses.databinding.Hw6FirstTaskActivityBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Serializable
import java.util.*



@RequiresApi(Build.VERSION_CODES.N)
class HW6FirstTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw6FirstTaskActivityBinding
    private val personList: MutableList<HW6PersonList> = mutableListOf()
    private val personListAdapter = HW6PersonListAdapter(personList, this)
    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


    companion object {
        const val PERSON_LIST: String = "person_list"
        const val DATE_FORMAT: String = "dd.MM.yyyy"
        const val REGEX: String = "[A-Za-zА-Яа-я]"
        const val FILE_NAME = "PersonInfoFile"
        const val FILE_NAME_EXTERNAL = "PersonInfoFileExternal"
        const val FILE_FOR_CACHE = "CacheFile"
        const val DIALOG_TITLE = "File checking"
        const val DIALOG_MESSAGE = "Clear file"
        const val NEGATIVE_ANSWER = "No"
        const val TITLE_FOR_DATE_PICKER = "Select date"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw6FirstTaskActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fileWithInfo = this.getFileStreamPath(FILE_NAME)
        if (fileWithInfo.exists()) {
            openAlertDialogForCheckingFile()
        }
        binding.writeButton.setOnClickListener {
            writeToInternalStorage()
            readFromInternalStorage()
            binding.nextScreen.isEnabled = true
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

        binding.nextScreen.setOnClickListener {
            val list = personListAdapter.getItems()
            val intent = Intent(applicationContext, HW6SecondActivity::class.java)
            intent.putExtra(PERSON_LIST, list as Serializable)
            val fileWithInfo = File(this.getExternalFilesDir(null), FILE_NAME_EXTERNAL)
            if (fileWithInfo.exists()) {
                openAlertDialogForCheckingFileInExternalStorage(intent)
            } else {
                writeToExternalStorage(list)
                startActivity(intent)
            }


        }

    }

    private fun readFromInternalStorage() {
        personListAdapter.clearInfo(personList)
        this.openFileInput(FILE_NAME).bufferedReader().useLines { lines ->
            lines.forEach {
                personListAdapter.addItems(parseList(it))
            }
        }
        binding.personList.layoutManager = layoutManager
        binding.personList.adapter = personListAdapter
    }

    private fun writeToInternalStorage() {
        val information =
            "${binding.nameEditText.editText?.text.toString()}|" +
                    "${binding.surnameEditText.editText?.text.toString()}|" +
                    "${binding.phoneEditText.editText?.text.toString().toInt()}|" +
                    "${binding.ageEditText.editText?.text.toString().toInt()}|" +
                    "${binding.dateEditText.text}\n"

        this.openFileOutput(FILE_NAME, Context.MODE_APPEND).use {
            it.write(information.toByteArray())
        }
    }

    private fun openAlertDialogForCheckingFile() {
        MaterialAlertDialogBuilder(this, R.style.CutShapeAppearance)
            .setTitle(DIALOG_TITLE)
            .setMessage(DIALOG_MESSAGE)
            .setPositiveButton(POSITIVE_ANSWER) { _, _ ->
                clearData()
            }
            .setNegativeButton(NEGATIVE_ANSWER) { _, _ ->
                readFromInternalStorage()
                binding.nextScreen.isEnabled = true
            }
            .show()
    }

    private fun parseList(info: String): HW6PersonList {
        val list = info.split('|')
        return HW6PersonList(
            list[0],
            list[1],
            list[2].toInt(),
            list[3].toInt(),
            SimpleDateFormat(DATE_FORMAT).parse(list[4])
        )
    }

    private fun clearData() {
        this.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write("".toByteArray())
        }
    }

    private fun clearDataForExternalStorage() {
        val file = File(this.getExternalFilesDir(null), FILE_NAME_EXTERNAL)
        BufferedWriter(FileWriter(file)).use {
            it.write("")
        }
    }

    private fun openAlertDialogForCheckingFileInExternalStorage(intent_: Intent) {
        MaterialAlertDialogBuilder(this, R.style.CutShapeAppearance)
            .setTitle(DIALOG_TITLE)
            .setMessage(DIALOG_MESSAGE)
            .setPositiveButton(POSITIVE_ANSWER) { _, _ ->
                clearDataForExternalStorage()
                startActivity(intent_)
            }
            .setNegativeButton(NEGATIVE_ANSWER) { _, _ ->
                writeToExternalStorage(
                    personListAdapter.getItems()
                )
                startActivity(intent_)
            }
            .show()

    }

    //внешняя память
    private fun writeToExternalStorage(list: MutableList<HW6PersonList>) {
        clearDataForExternalStorage()
        val externalState = Environment.getExternalStorageState()
        var information = ""
        list.forEach {
            information += it.toString()
        }
        if (externalState == Environment.MEDIA_MOUNTED) {
            val file = File(this.getExternalFilesDir(null), FILE_NAME_EXTERNAL)
            BufferedWriter(FileWriter(file, true)).use {
                it.write(replace(information))
            }
        }
    }

    private fun replace(info: String): String {
        return info.replace("[", "").replace("]", "").replace(", ", "")
    }

    //кэш
    private fun writeInfoToCacheFile() {
        val file = File(this.cacheDir, FILE_FOR_CACHE)

        val info = "${binding.nameEditText.editText?.text.toString()}|" +
                "${binding.surnameEditText.editText?.text.toString()}|" +
                "${binding.phoneEditText.editText?.text.toString().toInt()}|" +
                "${binding.ageEditText.editText?.text.toString().toInt()}|" +
                "${binding.dateEditText.text}\n"

        val bw = BufferedWriter(FileWriter(file.absoluteFile))
        bw.write(info)
        bw.close()
    }

    override fun onRestart() {
        super.onRestart()
        readInfoFromCacheFile()
    }

    override fun onStop() {
        super.onStop()
        writeInfoToCacheFile()
    }


    private fun readInfoFromCacheFile() {
        val file = File(this.cacheDir, FILE_FOR_CACHE)
        if (file.exists()) {
            val info = file.bufferedReader().use {
                it.readLine()
            }
            val list = info.split('|')
            binding.nameEditText.editText?.setText(list[0])
            binding.surnameEditText.editText?.setText(list[1])
            binding.phoneEditText.editText?.setText(list[2])
            binding.ageEditText.editText?.setText(list[3])
            binding.dateEditText.text = list[4]
        }

        file.delete()
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
