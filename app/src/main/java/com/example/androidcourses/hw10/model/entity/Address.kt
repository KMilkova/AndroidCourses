package com.example.androidcourses.hw10.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address_table")
data class Address(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name="city") val city:String,
    @ColumnInfo(name ="street") val street:String,
    @ColumnInfo(name="house_number") val house_number: String
)
