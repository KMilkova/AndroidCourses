package com.example.androidcourses.hw10.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidcourses.hw10.model.entity.PersonAddress

@Dao
interface PersonAddressDAO {

    @Query("SELECT * FROM person_address_table")
    fun getAll(): LiveData<List<PersonAddress>>

    @Insert
    fun insertAll(personAddress: PersonAddress)

    @Insert
    fun insertPersonAddress(personAddress: PersonAddress)

    @Delete
    fun deletePersonAddress(personAddress: PersonAddress)

    @Update
    fun updatePersonAddress(personAddress: PersonAddress)

    @Query("UPDATE person_address_table SET person_id = :personId, address_id = :addressId")
    fun updatePersonAddress(personId: Int, addressId: Int)

    @Query("UPDATE person_address_table SET person_id = :personId")
    fun updatePersonId(personId: Int)

    @Query("UPDATE person_address_table SET address_id = :addressId")
    fun updateAddressId(addressId: Int)

    @Query("SELECT COUNT(*) FROM person_address_table WHERE person_id = :personId")
    fun getCountOfAddressForPerson(personId: Int): Int

    @Query("SELECT address_id FROM person_address_table WHERE person_id = :personId")
    fun getAddressID(personId: Int):Int
}