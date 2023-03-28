package com.example.androidcourses

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw8ThirdTaskFullInfoActivityBinding
import com.example.androidcourses.room.AppDatabase
import com.example.androidcourses.room.entity.Person
import java.util.concurrent.Executors

class HW8ThirdTaskFullInfoActivity : AppCompatActivity() {
    lateinit var binding: Hw8ThirdTaskFullInfoActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8ThirdTaskFullInfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getInstance(this)

        val personDAO = db.personDao()
        Executors.newSingleThreadExecutor().execute {
            val personList = personDAO.getAll().toMutableList()
            runOnUiThread {
                binding.fullInfoRV.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.fullInfoRV.adapter = HW8ThirdTaskFullInfoAdapter(personList, db)
            }

        }

        binding.searchBtn.setOnClickListener {
            Executors.newSingleThreadExecutor().execute {
                val personList =
                    personDAO.getPersonBySurname(binding.editTextForSearchBySurname.text.toString())
                        .toMutableList()
                runOnUiThread {
                    binding.fullInfoRV.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.fullInfoRV.adapter = HW8ThirdTaskFullInfoAdapter(personList, db)
                }

            }
        }

        binding.showAllRdBtn.isChecked = true
        val radioGrp: RadioGroup = binding.conditionalRadioGroup
        radioGrp.setOnCheckedChangeListener { _, checkedId ->
            val isAll = checkedId == binding.showAllRdBtn.id
            var personList: MutableList<Person>
            Executors.newSingleThreadExecutor().execute {
                val personDao = db.personDao()
                personList = if (isAll) {
                    personDao.getAll().toMutableList()
                } else {
                    personDao.getPersonsWithAddress().toMutableList()
                }
                runOnUiThread {
                    binding.fullInfoRV.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.fullInfoRV.adapter = HW8ThirdTaskFullInfoAdapter(personList, db)
                }
            }

        }


    }
}