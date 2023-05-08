package com.example.androidcourses.hw9.model

import com.google.gson.annotations.SerializedName


data class Items(
   @SerializedName("products")
   var items:List<Item>
)