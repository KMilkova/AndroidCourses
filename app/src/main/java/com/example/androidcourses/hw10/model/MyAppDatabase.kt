package com.example.androidcourses.hw10.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidcourses.HW8ThirdTaskChooseAddressActivity
import com.example.androidcourses.hw10.model.dao.AddressDAO
import com.example.androidcourses.hw10.model.dao.PersonAddressDAO
import com.example.androidcourses.hw10.model.dao.PersonDAO
import com.example.androidcourses.hw10.model.entity.Address
import com.example.androidcourses.hw10.model.entity.Person
import com.example.androidcourses.hw10.model.entity.PersonAddress

@Database(
    entities = [Person::class, Address::class, PersonAddress::class],
    version = 1,
    exportSchema = true
)
abstract class MyAppDatabase: RoomDatabase()  {
    abstract fun personDao(): PersonDAO
    abstract fun addressDao(): AddressDAO
    abstract fun personAddressDao(): PersonAddressDAO
}