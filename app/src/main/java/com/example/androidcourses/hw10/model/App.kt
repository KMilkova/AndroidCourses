package com.example.androidcourses.hw10.model

import android.app.Application
import androidx.room.Room
import com.example.androidcourses.hw10.model.dao.AddressDAO
import com.example.androidcourses.hw10.model.dao.PersonDAO
import com.example.androidcourses.hw10.model.dao.PersonAddressDAO
import com.example.androidcourses.hw10.utils.Constants

class App : Application() {
    lateinit var personDAO: PersonDAO
    lateinit var personAddressDAO: PersonAddressDAO
    lateinit var addressDAO: AddressDAO

    override fun onCreate() {
        super.onCreate()
        instance = this

        val db = Room.databaseBuilder(this, MyAppDatabase::class.java, Constants.DB_NAME)
            .build()
        personAddressDAO = db.personAddressDao()
        addressDAO = db.addressDao()
        personDAO = db.personDao()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}