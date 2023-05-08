package com.example.androidcourses.hw9

import io.reactivex.rxjava3.core.Observable
import com.example.androidcourses.hw9.model.Item
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface RetrofitInterface {

    @GET("products")
    fun getItems(): Observable<List<Item>>

    @GET("products/{id}")
    fun getItem(
        @Path("id") id: Int
    ): Observable<Item>

    @POST("products")
    fun saveItem(@Body item: Item): Observable<Item>
}