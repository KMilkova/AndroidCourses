package com.example.androidcourses.hw9.utils

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Constants {
    companion object {
        private const val BASE_URL = "https://fakestoreapi.com/"
        const val SHARED_PREFERENCES_NAME = "item_information"
        const val ITEM_ID = "id"
        const val ERROR_MESSAGE = "Error found is : "
        const val DATA_ADD_MESSAGE = "Data added to API"
        const val RESPONSE_CODE = "Response Code : "
        const val TITLE = "\ntitle : "
        const val CATEGORY = "\ncategory : "
        const val PRICE = "\nprice : "
        const val DESCRIPTION = "smth"
        const val IMAGE_URL = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        fun initRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
    }
}