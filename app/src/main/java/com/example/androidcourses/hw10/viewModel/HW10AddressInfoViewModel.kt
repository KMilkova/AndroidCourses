package com.example.androidcourses.hw10.viewModel

import androidx.lifecycle.ViewModel
import com.example.androidcourses.hw10.model.App
import com.example.androidcourses.hw10.utils.AddressUtil
import java.util.concurrent.Executors

class HW10AddressInfoViewModel : ViewModel() {
    private val addressDAO = App.instance.addressDAO
    val addressList = addressDAO.getAll()

    init {
        insertAddresses()
    }

    private fun insertAddresses() {
        Executors.newSingleThreadExecutor().execute {
            if (addressDAO.rowCount() == 0) {
                val addresses = AddressUtil.fillAddresses()
                addressDAO.insertAll(addresses)
            }
        }
    }


}