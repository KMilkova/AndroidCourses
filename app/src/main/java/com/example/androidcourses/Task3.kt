package com.example.androidcourses

import kotlin.math.sqrt


fun main() {
    //задание 3a 1
    println(factorial(5))
    //задание 3a 2
    println(factorialRec(5))
    //задание 3b
    task3b()

}

//задание 3а
//1 факториал с циклом и диапазоном
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i
    }
    return result
}

//2 факториал рекурсия
fun factorialRec(n: Int): Double = if (n < 2) 1.0 else n * factorial(n - 1)

//задание 3b
//функция для нахождения простого числа
fun isPrime(n: Int): Boolean {
    var cnt: Int = 0
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            cnt++
        }
    }
    if (n < 2) {
        cnt = -1
    }
    return cnt == 0
}

//функция для нахождения простых чисел и добавления их в массив и лист
fun task3b() {
    var simpleInArray: Array<Int> = Array(10) { 0 }
    var simpleInList: MutableList<Int> = mutableListOf()
    var k: Int = 0
    for (i in 2..10000) {
        if (isPrime(i)) {
            if (simpleInList.size < 20) {
                simpleInList.add(i)
            } else if (k < 10) {
                simpleInArray[k] = i
                k++
            }
        }

    }
    print(simpleInList)
    println()
    for (i in simpleInArray) {
        print("$i ")
    }
}
