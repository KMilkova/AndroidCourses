package com.example.androidcourses.hw10.view.activities

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidcourses.databinding.Hw8ThirdTaskChooseAddressActivityBinding
import com.example.androidcourses.hw10.model.entity.PersonAddress
import com.example.androidcourses.hw10.utils.Constants
import com.example.androidcourses.hw10.viewModel.HW10ChooseAddressViewModel

class HW10ChooseAddressActivity : AppCompatActivity() {
    private lateinit var binding: Hw8ThirdTaskChooseAddressActivityBinding
    private lateinit var chooseAddressViewModel: HW10ChooseAddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8ThirdTaskChooseAddressActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chooseAddressViewModel = ViewModelProvider(this)[HW10ChooseAddressViewModel::class.java]


        val shared = this.getSharedPreferences(
            Constants.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val name = shared.getString(Constants.PERSON_NAME, "default")
        val surname = shared.getString(Constants.PERSON_SURNAME, "default")
        val phone = shared.getInt(Constants.PERSON_PHONE, 0)
        val age = shared.getInt(Constants.PERSON_AGE, 0)
        val birthday = shared.getString(Constants.PERSON_BIRTHDAY, "default")
        val id = shared.getInt(Constants.PERSON_ID, 0)

        with(binding) {
            nameTextView.text = name
            surnameTextView.text = surname
            phoneTextView.text = phone.toString()
            ageTextView.text = age.toString()
            birthdayTextView.text = birthday
        }


        val addressFullList = mutableListOf<String>()

        chooseAddressViewModel.addressLiveData.observe(this) { addresses ->
            addresses.forEach {
                val res = it.id.toString() + " " + it.city + " " + it.street + " " + it.house_number
                addressFullList.add(res)
            }
            val adapter =
                ArrayAdapter(this, R.layout.simple_spinner_item, addressFullList)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.addressesSpinner.adapter = adapter

            val itemSelectedListener: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        binding.addressesSpinner.setSelection(position)
                        val item = parent.getItemAtPosition(position)
                        binding.selection.text = item.toString()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            binding.addressesSpinner.onItemSelectedListener = itemSelectedListener


        }

        binding.saveAddress.setOnClickListener {
            chooseAddressViewModel.insertIntoPersonAddressTable(
                PersonAddress(
                    0,
                    id,
                    getAddressID(binding.selection.text.toString())
                )
            )
            Toast.makeText(
                this,
                chooseAddressViewModel.personAddress.toString(),
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(
                this, HW10FullInfoActivity::class.java
            )
            startActivity(intent)
        }

        binding.showPersonAddress.setOnClickListener {
            val intent = Intent(this, HW10FullInfoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getAddressID(address: String): Int {
        val id = address.substring(0, address.indexOf(' '))
        return id.toInt()
    }
}