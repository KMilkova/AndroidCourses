package com.example.androidcourses.hw9

import com.example.androidcourses.hw9.shop.Item
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface RetrofitInterface {

    @GET("products")
    fun getItems(): Call<List<Item>>

    @GET("products/{id}")
    fun getItem(
        @Path("id") id: Int
    ): Call<Item>

    @POST("products")
    fun saveItem(@Body item: Item): Call<Item>
}