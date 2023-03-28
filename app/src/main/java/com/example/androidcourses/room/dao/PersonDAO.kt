package com.example.androidcourses.room.dao

import androidx.room.*
import com.example.androidcourses.room.entity.Person

@Dao
interface PersonDAO {
    @Query("SELECT * FROM person_table")
    fun getAll(): List<Person>

    @Query("SELECT * FROM person_table WHERE person_name LIKE :name")
    fun getPersonByName(name: String): List<Person>

    @Query("SELECT * FROM person_table WHERE person_surname LIKE :surname")
    fun getPersonBySurname(surname: String): List<Person>

    @Query("SELECT * FROM person_table WHERE person_age BETWEEN :startAge AND :endAge")
    fun getPersonByAgeRange(startAge: Int, endAge: Int): List<Person>

    @Query("SELECT * FROM person_table LIMIT :cnt")
    fun getFewPersons(cnt: Int): List<Person>


    @Insert
    fun insertAll(persons: List<Person>)

    @Insert
    fun insertPerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

    @Update
    fun updatePerson(person: Person)

    @Query(
        "UPDATE person_table SET person_name = :name, person_surname = :surname, person_phone = :phone, person_age = :age, person_birthday = :birthday WHERE id = :personId"
    )
    fun updatePerson(
        personId: Int,
        name: String,
        surname: String,
        phone: Int,
        age: Int,
        birthday: String
    )

    @Query("UPDATE person_table SET person_name = :name WHERE id = :personId")
    fun updatePersonName(personId: Int, name: String)

    @Query("UPDATE person_table SET person_surname = :surname WHERE id = :personId")
    fun updatePersonSurname(personId: Int, surname: String)

    @Query("UPDATE person_table SET person_phone = :phone WHERE id = :personId")
    fun updatePersonPhone(personId: Int, phone: Int)

    @Query("UPDATE person_table SET person_age = :age WHERE id = :personId")
    fun updatePersonAge(personId: Int, age: Int)

    @Query("UPDATE person_table SET person_birthday = :birthday WHERE id = :personId")
    fun updatePersonBirthday(personId: Int, birthday: String)


    @Query("SELECT * FROM person_table WHERE id IN (SELECT person_id FROM person_address_table)")
    fun getPersonsWithAddress(): List<Person>


}