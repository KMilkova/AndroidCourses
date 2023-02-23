package com.example.androidcourses

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw4ThirdTaskThirdActivityBinding
import java.util.Date

class TaskThreeThirdActivity:AppCompatActivity() {
    lateinit var binding:Hw4ThirdTaskThirdActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding = Hw4ThirdTaskThirdActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name: String? = intent.getStringExtra(NAME)
        val surname: String? = intent.getStringExtra(SURNAME)
        val phone: Int = intent.getIntExtra(PHONE, 0)
        val age: Int = intent.getIntExtra(AGE, 0)
        val gender:Boolean = intent.getBooleanExtra(GENDER,true)
        val email:String? = intent.getStringExtra(EMAIL)
        val birthday: String? =  intent.getStringExtra(BIRTHDAY)

        binding.nameTextView.text = name
        binding.surnameTextView.text = surname
        binding.phoneTextView.text = phone.toString()
        binding.ageTextView.text = age.toString()
        if (gender){
            binding.genderTextView.text = "woman"
            binding.genderTextView.setTextColor(parseColor("#CA94FF"))
        }
        else{
            binding.genderTextView.text = "man"
            binding.genderTextView.setTextColor(parseColor("#94BFFF"))
        }
        binding.emailTextView.text = email
        binding.birthdayTextView.text = birthday.toString()

        binding.phoneTextView.setOnClickListener {
            var callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+375"+binding.phoneTextView.text.toString()));
            startActivity(callIntent)
        }

        binding.emailTextView.setOnClickListener{
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:"+binding.emailTextView.text.toString())
            }
            startActivity(Intent.createChooser(emailIntent, "Send feedback"))
        }


    }
}