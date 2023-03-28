package com.example.androidcourses.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidcourses.HW8ThirdTaskChooseAddressActivity
import com.example.androidcourses.room.dao.AddressDAO
import com.example.androidcourses.room.dao.PersonAddressDAO
import com.example.androidcourses.room.dao.PersonDAO
import com.example.androidcourses.room.entity.Address
import com.example.androidcourses.room.entity.Person
import com.example.androidcourses.room.entity.PersonAddress

@Database(
    entities = [Person::class, Address::class, PersonAddress::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDAO
    abstract fun addressDao(): AddressDAO
    abstract fun personAddressDao(): PersonAddressDAO

    companion object {
        private var database: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    HW8ThirdTaskChooseAddressActivity.DB_NAME
                ).build()
            }
            return database as AppDatabase
        }
    }
}