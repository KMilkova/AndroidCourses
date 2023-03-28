package com.example.androidcourses

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw8SecondTaskActivityBinding

class HW8SecondTaskActivity : AppCompatActivity() {
    lateinit var binding: Hw8SecondTaskActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8SecondTaskActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared = this.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val name = shared.getString(PERSON_NAME, "default")
        val surname = shared.getString(PERSON_SURNAME, "default")
        val phone = shared.getInt(PERSON_PHONE, 0)
        val age = shared.getInt(PERSON_AGE, 0)
        val birthday = shared.getString(PERSON_BIRTHDAY, "default")

        with(binding) {
            nameTextView.text = name
            surnameTextView.text = surname
            phoneTextView.text = phone.toString()
            ageTextView.text = age.toString()
            birthdayTextView.text = birthday

            when (age) {
                in 0..10 -> picture.setImageResource(R.drawable._527740382)
                in 11..20 -> picture.setImageResource(R.drawable._401833208)
                in 21..30 -> picture.setImageResource(R.drawable.pipsie)
                in 31..45 -> picture.setImageResource(R.drawable.corgi)
                in 46..60 -> picture.setImageResource(R.drawable._544556490)
                in 61..70 -> picture.setImageResource(R.drawable._292959773)
                in 71..99 -> picture.setImageResource(R.drawable._287430037)
            }
        }
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "person_information"
        const val PERSON_NAME = "name"
        const val PERSON_SURNAME = "surname"
        const val PERSON_PHONE = "phone"
        const val PERSON_AGE = "age"
        const val PERSON_BIRTHDAY = "birthday"
        const val PERSON_ID = "id"

    }
}