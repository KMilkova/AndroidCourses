package com.example.androidcourses

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourses.databinding.Hw8ThirdTaskChooseAddressActivityBinding
import com.example.androidcourses.room.AppDatabase
import com.example.androidcourses.room.entity.Address
import com.example.androidcourses.room.entity.PersonAddress
import java.util.concurrent.Executors


class HW8ThirdTaskChooseAddressActivity : AppCompatActivity() {
    lateinit var binding: Hw8ThirdTaskChooseAddressActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8ThirdTaskChooseAddressActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db = AppDatabase.getInstance(this)
        val addressDAO = db.addressDao()

        val shared = this.getSharedPreferences(
            HW8SecondTaskActivity.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val name = shared.getString(HW8SecondTaskActivity.PERSON_NAME, "default")
        val surname = shared.getString(HW8SecondTaskActivity.PERSON_SURNAME, "default")
        val phone = shared.getInt(HW8SecondTaskActivity.PERSON_PHONE, 0)
        val age = shared.getInt(HW8SecondTaskActivity.PERSON_AGE, 0)
        val birthday = shared.getString(HW8SecondTaskActivity.PERSON_BIRTHDAY, "default")
        val id = shared.getInt(HW8SecondTaskActivity.PERSON_ID, 0)

        with(binding) {
            nameTextView.text = name
            surnameTextView.text = surname
            phoneTextView.text = phone.toString()
            ageTextView.text = age.toString()
            birthdayTextView.text = birthday
        }
        var check = false

        val personAddress = db.personAddressDao()
        Executors.newSingleThreadExecutor().execute {
            if (personAddress.getCountOfAddressForPerson(id) != 0) {
                check = true
            }
            runOnUiThread {
                if (check) {
                    binding.saveAddress.isEnabled = false
                    binding.addressesSpinner.isEnabled = false

                } else {
                    binding.saveAddress.isEnabled = true
                    binding.addressesSpinner.isEnabled = true
                }
            }
        }



        Executors.newSingleThreadExecutor().execute {
            val addressList = addressDAO.getAll() as MutableList<Address>
            val addressFullList = mutableListOf<String>()


            addressList.forEach {
                val res = it.id.toString() + " " + it.city + " " + it.street + " " + it.house_number
                addressFullList.add(res)
            }
            val adapter =
                ArrayAdapter(this, R.layout.simple_spinner_item, addressFullList as List<Any>)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.addressesSpinner.adapter = adapter

            val itemSelectedListener: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val item = parent.getItemAtPosition(position) as String
                        binding.selection.text = item
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            binding.addressesSpinner.onItemSelectedListener = itemSelectedListener
        }

        binding.saveAddress.setOnClickListener {

            Executors.newSingleThreadExecutor().execute {
                val personAddress = db.personAddressDao()
                personAddress.insertPersonAddress(
                    PersonAddress(
                        0,
                        id,
                        getAddressID(binding.selection.text.toString())
                    )
                )
            }
            val intent = Intent(this, HW8ThirdTaskFullInfoActivity::class.java)
            startActivity(intent)
        }

        binding.showPersonAddress.setOnClickListener {
            val intent = Intent(this, HW8ThirdTaskFullInfoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getAddressID(address: String): Int {
        val id = address.substring(0, address.indexOf(' '))
        return id.toInt()
    }

    companion object {
        const val DB_NAME = "persons"
    }
}
