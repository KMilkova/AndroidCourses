package com.example.androidcourses


fun main() {
    val checkedAge = 5
    val checkedWrongAge = 150
    checkingAge(checkedAge)
    checkingAge(checkedWrongAge)
    checkDivisionByZero(2, 0)
    checkDivisionByZeroSecond(5, 0)
    functionWithMoreThanOneCatch(6, 0)
}

//Задание 7а проверка возраста
fun checkingAge(age: Int): Int {
    if (age < 0 || age > 110) {
        throw Exception("Invalid age $age")
    }
    println("Age $age is valid")
    return age
}

//Задание 7b try-catch
fun checkDivisionByZero(firstNumber: Int, secondNumber: Int) {
    try {
        println(firstNumber / secondNumber)
    } catch (e: ArithmeticException) {
        println("Exception")
        println(e.message)
    }
}

//Задание 7c try-выражение и задание 7f finally
fun checkDivisionByZeroSecond(firstNumber: Int, secondNumber: Int) {
    val res = try {
        println(firstNumber / secondNumber)
    } catch (e: ArithmeticException) {
        println("Exception")
        println(e.message)
    } finally {
        println("Program has been finished")
    }

}

//Задание 7d множественные блоки catch
fun functionWithMoreThanOneCatch(firstNumber: Int, secondNumber: Int) {
    try {
        val res = firstNumber / secondNumber
    } catch (e: ArithmeticException) {
        println("Exception")
        println(e.message)
    } catch (e: NullPointerException) {
        println("Exception")
        println(e.message)
    } catch (e: Exception) {
        println("Exception")
        println(e.message)
    }
}
