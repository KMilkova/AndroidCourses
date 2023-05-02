package com.example.androidcourses.hw10.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "person_name") val personName: String,
    @ColumnInfo(name = "person_surname") val personSurname: String,
    @ColumnInfo(name = "person_phone") val personPhone: Int,
    @ColumnInfo(name = "person_age") val personAge: Int,
    @ColumnInfo(name = "person_birthday") val personBirthday: String
)