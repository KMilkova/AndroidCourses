package com.example.androidcourses

fun main() {

    //задание 1а переменные с val и var
    val variableString: String = "variableString"
    var variableDouble: Double = 1.2
    val variableInt: Int = 152

    val variableOne = 2.1
    var variableTwo = 1
    val variableThree = "variableWithoutType"

    //задание 1b преобразование
    val variableByte: Byte = 4
    val resultOfConvert = variableByte.toInt()

    val variableIntForConvert = 125
    val resultOfConvert2 = variableIntForConvert.toString()

    //задание 1c вывод значения
    println("Переменная типа после преобразования инт $resultOfConvert")
    println("Переменная типа после преобразования стринг $resultOfConvert2")

    //задание 1e
    val nullValue : Int? = readLine() as? Int

}
//задание 1d
const val constantVariable = "constString"
