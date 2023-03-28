package com.example.androidcourses.room.dao

import androidx.room.*
import com.example.androidcourses.room.entity.Address

@Dao
interface AddressDAO {
    @Query("SELECT * FROM address_table")
    fun getAll(): List<Address>

    @Query("SELECT * FROM address_table WHERE city LIKE :city")
    fun getAddressByCity(city: String): List<Address>

    @Query("SELECT * FROM address_table WHERE street LIKE :street")
    fun getAddressByStreet(street: String): List<Address>

    @Query("SELECT * FROM address_table WHERE house_number LIKE :houseNumber")
    fun getAddressByHouseNumber(houseNumber: Int): List<Address>

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

}