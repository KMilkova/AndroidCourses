package com.example.androidcourses

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw6SecondTaskBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class HW6SecondActivity : AppCompatActivity() {
    private lateinit var binding: Hw6SecondTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw6SecondTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personList: MutableList<HW6PersonList> =
            intent.getSerializableExtra(PERSON_LIST) as MutableList<HW6PersonList>
        val personListAdapter = HW6CardAdapter(personList, applicationContext)
        binding.recViewForCard.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recViewForCard.adapter = personListAdapter
        binding.linearLayoutRadioButton.isChecked = true
        var isLinear = true
        val radioGrp: RadioGroup = binding.layoutTypeRadioGroup
        radioGrp.setOnCheckedChangeListener { group, checkedId ->
            val rb: RadioButton = findViewById<RadioButton>(checkedId)
            isLinear = checkedId == binding.linearLayoutRadioButton.id
            Log.d("checked", "${rb.text}")
            val layoutManager = if (!isLinear) {
                GridLayoutManager(this, 2)
            } else {
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            }
            binding.recViewForCard.layoutManager = layoutManager
            binding.recViewForCard.adapter = personListAdapter
        }

        binding.menuView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.complimentItem -> openAlertDialog()
                R.id.instaItem -> {
                    val uri: Uri = Uri.parse("http://instagram.com/n_milkovaa")
                    val likeIng = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(likeIng)
                }
                R.id.youtubeItem ->{
                    val uri: Uri = Uri.parse("https://www.youtube.com/shorts/EsjbelGLtao")
                    val youtube = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(youtube)
                }
                R.id.additionalTaskActivity ->{
                    val intent = Intent(applicationContext,AdditionalTaskActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    private fun openAlertDialog(){
        MaterialAlertDialogBuilder(this, R.style.CutShapeAppearance)
            .setTitle("Compliment")
            .setMessage("You are pretty")
            .setPositiveButton("Yes") { _, i -> }
            .show()
    }


}