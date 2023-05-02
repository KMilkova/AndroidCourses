package com.example.androidcourses.hw10.viewModel


import androidx.lifecycle.ViewModel
import com.example.androidcourses.hw10.model.App
import com.example.androidcourses.hw10.model.entity.Address
import com.example.androidcourses.hw10.model.entity.PersonAddress
import java.util.concurrent.Executors

class HW10ChooseAddressViewModel : ViewModel() {
    private val addressDAO = App.instance.addressDAO
    private val personAddressDAO = App.instance.personAddressDAO

    val addressLiveData = addressDAO.getAll()
    val personAddress = personAddressDAO.getAll()

    fun isPersonHasAddress(id: Int):Boolean {
        var check = true
        Executors.newSingleThreadExecutor().execute {
            println(personAddressDAO.getCountOfAddressForPerson(id))
            if(personAddressDAO.getCountOfAddressForPerson(id)==0){
                check = false
            }
        }
        return check
    }


    fun getAll(): List<Address>? {
        return addressLiveData.value
    }

    fun insertIntoPersonAddressTable(personAddress: PersonAddress) {
        Executors.newSingleThreadExecutor().execute {
            personAddressDAO.insertPersonAddress(personAddress)
        }
    }


}