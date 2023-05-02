package com.example.androidcourses.hw10.viewModel

import androidx.lifecycle.ViewModel
import com.example.androidcourses.hw10.model.App
import com.example.androidcourses.hw10.model.entity.Person
import java.util.concurrent.Executors

class HW10MainActivityViewModel : ViewModel() {
    private val personDAO = App.instance.personDAO
    internal var personList = personDAO.getAllEl()

    fun insertElToDB(person: Person) {
        Executors.newSingleThreadExecutor().execute {
            personDAO.insertPerson(person)
        }
    }

    fun deleteElFromDB(person: Person) {
        Executors.newSingleThreadExecutor().execute {
            personDAO.deletePerson(person)
        }
    }
}

