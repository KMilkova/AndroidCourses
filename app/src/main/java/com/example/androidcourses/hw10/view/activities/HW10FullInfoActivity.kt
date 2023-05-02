package com.example.androidcourses.hw10.view.activities

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw8ThirdTaskFullInfoActivityBinding
import com.example.androidcourses.hw10.model.entity.Person
import com.example.androidcourses.hw10.view.adapters.HW10FullInfoAdapter
import com.example.androidcourses.hw10.viewModel.HW10FullInfoViewModel

class HW10FullInfoActivity : AppCompatActivity() {
    lateinit var binding: Hw8ThirdTaskFullInfoActivityBinding
    private lateinit var fullInfoViewMode: HW10FullInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8ThirdTaskFullInfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullInfoViewMode = ViewModelProvider(this)[HW10FullInfoViewModel::class.java]

        fullInfoViewMode.personLiveData.observe(this) {
            binding.fullInfoRV.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.fullInfoRV.adapter = HW10FullInfoAdapter(it.toMutableList(), fullInfoViewMode,this)
        }


        binding.searchBtn.setOnClickListener {
            val personList =
                fullInfoViewMode.getPersonsBySurname(binding.editTextForSearchBySurname.text.toString()) as MutableList<Person>
            binding.fullInfoRV.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.fullInfoRV.adapter = HW10FullInfoAdapter(personList, fullInfoViewMode,this)
        }

        binding.showAllRdBtn.isChecked = true
        val radioGrp: RadioGroup = binding.conditionalRadioGroup
        radioGrp.setOnCheckedChangeListener { _, checkedId ->
            val isAll = checkedId == binding.showAllRdBtn.id
            val personList: MutableList<Person> = if (isAll) {
                fullInfoViewMode.getAll()!!.toMutableList()
            } else {
                fullInfoViewMode.getPersonsWithAddress().toMutableList()

            }

            binding.fullInfoRV.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.fullInfoRV.adapter = HW10FullInfoAdapter(personList, fullInfoViewMode,this)


        }

    }
}