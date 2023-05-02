package com.example.androidcourses.hw10.utils

import com.example.androidcourses.hw10.model.entity.Address
import com.example.androidcourses.room.entity.HW8Address

class AddressUtil {

    companion object{
    fun fillAddresses(): MutableList<Address> =
        mutableListOf(
            Address(0, "Минск", "Плеханова", "97"),
            Address(0, "Минск", "Плеханова", "105"),
            Address(0, "Минск", "Плеханова", "20"),
            Address(0, "Минск", "Якубова", "6"),
            Address(0, "Минск", "Якубова", "108"),
            Address(0, "Минск", "Уручье", "20"),
            Address(0, "Минск", "Уручье", "55"),
            Address(0, "Брест", "Высоцкая", "101"),
            Address(0, "Минск", "Вокзальная", "10"),
            Address(0, "Минск", "Вокзальная", "7"),
            Address(0, "Минск", "Якубова", "18")
        )

    }
}