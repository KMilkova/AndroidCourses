package com.example.androidcourses

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HW8DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NAME TEXT, " +
                    "$COLUMN_SURNAME TEXT, " +
                    "$COLUMN_PHONE INTEGER, " +
                    "$COLUMN_AGE INTEGER, " +
                    "$COLUMN_BIRTHDAY TEXT);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    companion object {
        const val DATABASE_NAME = "personList.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "person"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_SURNAME = "surname"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_AGE = "age"
        const val COLUMN_BIRTHDAY = "birthday"
    }
}