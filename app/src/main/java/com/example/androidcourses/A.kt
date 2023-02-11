package com.example.androidcourses

//task6
class A {
    private lateinit var prop: String
    private fun setUp() {
        prop = "100 + 200"
    }

    fun display() {
        setUp()
        println(prop)
    }
}
fun main(args: Array<String>) {
    val a = A()
    a.display()
}
