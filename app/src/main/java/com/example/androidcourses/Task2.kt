package com.example.androidcourses

import java.util.Date
import java.util.regex.Pattern

fun main() {
    //задание 2а
    println(sum(2.1, 5.3, 2.2))
    //задание 2b
    isValid("dadsad@gmail.com", "566ogggg879")
    //задание 2c
    findDate(1, 1, 2023)
    //задание 2d
    val result = doOperation(11, 5, '/');
    if (result != null) {
        println(result)
    } else {
        println("нет такой операции")
    }

    val array: IntArray = intArrayOf()
    for (el in array) {
        print("$el ")
    }
    println()
    var index: Int? = indexOfMax(array)
    if (index != null) {
        println("Индекс максимального элемента - $index ")
    } else {
        println("Массив пустой либо таких элементов много")
    }

    val check:String = "null"
    println(check.indexOfMax(array))
}

//задание 2а ф-ция sum с переменным числом аргументов типа Double
fun sum(vararg value: Double): Double {
    var sum = 0.0
    for (v in value) {
        sum += v
    }
    return sum;
}

//задание 2b написать ф-цию для проверки корректности ввода логина и пароля
fun isValid(login: String, password: String) {
    if (isPasswordValid(password) && isEmailValid(login)) {
        println("data is valid")
    } else println("data is not valid")
}

//ф-ция для проверки на пустоту
fun notNull(variableForCheck: String): Boolean {
    return variableForCheck.isNotEmpty()
}

//паттерны на проверку данных
val EMAIL_ADDRESS: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)
val PASSWORD: Pattern = Pattern.compile("(?!.*\\s).{7,12}$")

//ф-ции для проверки данных
fun isEmailValid(email: String): Boolean {
    return if (notNull(email)) {
        EMAIL_ADDRESS.matcher(email).matches()
    } else {
        false
    }
}

fun isPasswordValid(password: String): Boolean {
    return if (notNull(password)) {
        PASSWORD.matcher(password).matches()
    } else {
        return false
    }
}

//задание 2c
enum class Holidays(val day: Int, val month: Int, val year: Int) {
    NEW_YEAR(1, 1, 2023)
}

fun findDate(day: Int, month: Int, year: Int) {
    val date: Date = Date(day, month, year)
    when (date) {

    }
}

//задание 2d
fun doOperation(a: Int, b: Int, operation: Char): Double? {
    return when (operation) {
        '+' -> (a + b).toDouble()
        '-' -> (a - b).toDouble()
        '*' -> (a * b).toDouble()
        '/' -> (a / b.toDouble())
        '%' -> (a % b).toDouble()
        else -> null
    }
}

//задание 2e индекс самого большого элемента
fun indexOfMax(a: IntArray): Int? {
    var maxEl: Int = Int.MIN_VALUE
    var index: Int? = null
    if (a.isEmpty()) {
        return null
    }
    for (i in a.indices) {
        if (a[i] > maxEl) {
            maxEl = a[i]
            index = i
        } else if (a[i] == maxEl) {
            index = null
        }
    }
    return index
}

//задание 2e индекс самого большого элемента. расширение
fun String.indexOfMax(a: IntArray): Int? {
    var maxEl: Int = Int.MIN_VALUE
    var index: Int? = null
    if (a.isEmpty()) {
        return null
    }
    for (i in a.indices) {
        if (a[i] > maxEl) {
            maxEl = a[i]
            index = i
        } else if (a[i] == maxEl) {
            index = null
        }
    }
    return index
}

//задание 2f

