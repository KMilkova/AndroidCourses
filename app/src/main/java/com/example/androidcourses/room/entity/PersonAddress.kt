package com.example.androidcourses.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "person_address_table",
    foreignKeys = [ForeignKey(
        entity = Person::class,
        parentColumns = ["id"],
        childColumns = ["person_id"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Address::class,
        parentColumns = ["id"],
        childColumns = ["address_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
class PersonAddress(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "person_id") val personId: Int,
    @ColumnInfo(name = "address_id") val addressId: Int
)