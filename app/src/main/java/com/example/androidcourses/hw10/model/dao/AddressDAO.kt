package com.example.androidcourses.hw10.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidcourses.hw10.model.entity.Address

@Dao
interface AddressDAO {
    @Query("SELECT * FROM address_table")
    fun getAll(): LiveData<List<Address>>

    @Query("SELECT * FROM address_table")
    fun getAllEl(): List<Address>

    @Query("SELECT * FROM address_table WHERE city LIKE :city")
    fun getAddressByCity(city: String): LiveData<List<Address>>

    @Query("SELECT * FROM address_table WHERE street LIKE :street")
    fun getAddressByStreet(street: String): LiveData<List<Address>>

    @Query("SELECT * FROM address_table WHERE house_number LIKE :houseNumber")
    fun getAddressByHouseNumber(houseNumber: Int): LiveData<List<Address>>

    @Query("SELECT * FROM address_table WHERE id = :id")
    fun getAddressById(id: Int): List<Address>

    @Insert
    fun insertAll(addresses: List<Address>)

    @Insert
    fun insertAddress(address: Address)

    @Delete
    fun deleteAddress(address: Address)

    @Update
    fun updateAddress(address: Address)

    @Query("UPDATE address_table SET city = :city, street = :street, house_number = :houseNumber WHERE id = :addressID")
    fun updateAddress(addressID: Int, city: String, street: String, houseNumber: Int)

    @Query("UPDATE address_table SET city = :city WHERE id = :addressID")
    fun updateCity(addressID: Int, city: String)

    @Query("UPDATE address_table SET street = :street WHERE id = :addressID")
    fun updateStreet(addressID: Int, street: String)

    @Query("UPDATE address_table SET house_number = :houseNumber WHERE id = :addressID")
    fun updateHouseNumber(addressID: Int, houseNumber: Int)

    @Query("SELECT * FROM address_table at inner join person_address_table pat ON at.id = pat.address_id WHERE pat.person_id = :personId")
    fun getAddressByPersonId(personId: Int): Address

    @Query("Select count(*) from address_table")
    fun rowCount():Int

}