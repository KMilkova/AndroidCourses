package com.example.androidcourses.hw10.viewModel

import androidx.lifecycle.ViewModel
import com.example.androidcourses.hw10.model.App
import com.example.androidcourses.hw10.model.entity.Person
import java.util.concurrent.Executors

class HW10FullInfoViewModel : ViewModel() {
    private val personDAO = App.instance.personDAO
    private val addressDAO = App.instance.addressDAO
    private val personAddressDAO = App.instance.personAddressDAO

    var personList = mutableListOf<Person>()
    val addressLiveData = addressDAO.getAll()
    val personLiveData = personDAO.getAllEl()

    fun getPersonsBySurname(surname: String): List<Person> {
        Executors.newSingleThreadExecutor().execute {
            personList = personDAO.getPersonBySurname(surname) as MutableList<Person>
        }
        return personList
    }

    fun getPersonsWithAddress(): MutableList<Person> {
        Executors.newSingleThreadExecutor().execute {
            personList = personDAO.getPersonsWithAddress() as MutableList<Person>
        }
        return personList
    }

    fun getAll(): List<Person>? {
        return personLiveData.value
    }

    private var idAddress = 0
    fun getAddressId(id: Int):Int{
        Executors.newSingleThreadExecutor().execute {
            idAddress = personAddressDAO.getAddressID(id)
        }
        return idAddress
    }

}