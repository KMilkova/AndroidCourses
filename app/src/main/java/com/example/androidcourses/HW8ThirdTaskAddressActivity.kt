package com.example.androidcourses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourses.databinding.Hw8ThirdTaskAddressActivityBinding
import com.example.androidcourses.room.AppDatabase
import com.example.androidcourses.room.entity.Address
import java.util.concurrent.Executors

class HW8ThirdTaskAddressActivity : AppCompatActivity() {
    lateinit var binding: Hw8ThirdTaskAddressActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Hw8ThirdTaskAddressActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getInstance(this)
        val addressDao = db.addressDao()

        Executors.newSingleThreadExecutor().execute {
            addressDao.insertAll(fillAddresses())
            val addressList = addressDao.getAll() as MutableList<Address>

            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            runOnUiThread {
                binding.addressList.layoutManager = layoutManager
                binding.addressList.adapter = HW8ThirdTaskAddressInfoAdapter(addressList)
            }
        }


    }


    private fun fillAddresses(): MutableList<Address> {
        val list: MutableList<Address> = mutableListOf()
        list.add(Address(0, "Минск", "Плеханова", "97"))
        list.add(Address(0, "Минск", "Плеханова", "105"))
        list.add(Address(0, "Минск", "Плеханова", "20"))
        list.add(Address(0, "Минск", "Якубова", "6"))
        list.add(Address(0, "Минск", "Якубова", "108"))
        list.add(Address(0, "Минск", "Уручье", "20"))
        list.add(Address(0, "Минск", "Уручье", "55"))
        list.add(Address(0, "Брест", "Высоцкая", "101"))
        list.add(Address(0, "Минск", "Вокзальная", "10"))
        list.add(Address(0, "Минск", "Вокзальная", "7"))
        list.add(Address(0, "Минск", "Якубова", "18"))
        return list
    }
}