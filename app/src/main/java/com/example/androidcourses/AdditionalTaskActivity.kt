package com.example.androidcourses

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.AdditionalTaskBinding

class AdditionalTaskActivity : AppCompatActivity() {
    lateinit var binding: AdditionalTaskBinding
    lateinit var imv: ImageView
    lateinit var imv2: ImageView
    lateinit var layout: LinearLayout
    lateinit var textView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additional_task)


        //поворот картинки
        imv = findViewById(R.id.degreeImageView)
        imv.rotation = imv.rotation + 90

        //подчеркивание текста
        textView = findViewById(R.id.crossText)
        textView.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG.inv() }


        //нажатие на картинку
        imv2 = findViewById(R.id.view)
        layout = findViewById(R.id.layoutForView)
        var isClick: Boolean = false
        layout.setOnClickListener {
            if (isClick) {
                imv2.setImageResource(R.drawable.wasp_bee_icon_246571)
            } else {
                imv2.setImageResource(R.drawable.palm_tree_holidays_icon_246590)
            }
            isClick=!isClick
        }
    }
}