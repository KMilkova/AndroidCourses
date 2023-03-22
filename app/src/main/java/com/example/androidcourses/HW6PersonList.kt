package com.example.androidcourses

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.androidcourses.HW6FirstTaskActivity.Companion.DATE_FORMAT
import java.util.Date

@RequiresApi(Build.VERSION_CODES.N)
data class HW6PersonList(
    val name: String,
    val surname: String,
    val phone: Int,
    val age: Int,
    val birthday: Date
) : java.io.Serializable {

    override fun toString(): String {
        return "${name}|${surname}|${phone}|${age}|${
            SimpleDateFormat(DATE_FORMAT).format(birthday)
        }\n"
    }
}