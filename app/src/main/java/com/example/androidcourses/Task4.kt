package com.example.androidcourses

import kotlinx.coroutines.sync.Mutex

//задание 4а
//fun containsIn(collection: Collection<Any>): Boolean = collection.any { TODO() }

//задание 4b
fun task4b() {
    var list: MutableList<Int> = mutableListOf(1, 5, 3, 6, 7, 9)

    //add
    list.add(5)
    list.add(6)
    list += 10
    println(list)

    //uniq el
    val uniqList: MutableList<Int> = list.distinct() as MutableList<Int>
    println(uniqList)

    //foreach
    list.forEach { print("$it ") }
    println()

    //filter and nechetn el
    var nechetnList = list.filter { it % 2 != 0 }
    nechetnList.forEach { print("$it ") }
    println()

    //isSimple el
    var isSimpleList = list.filter { isPrime(it) }
    isSimpleList.forEach { print("$it ") }
    println()

    //find, groupBy, all, any
    println(list.find { it == 9 })

    println(list.groupBy { it % 2 == 0 })

    println(list.all { it > 2 })

    println(list.any { it > 2 })

    //distruct
    val (el1, el2, _, _, _) = list
    println("$el1, $el2")
}

fun main() {
    println("Task 4b")
    task4b()
    println("Task 4c")
    task4c()
}

//не придумала решение
fun task4c() {
    val person = mapOf(35 to "Student")
    var marks = mapOf(
        40 to 10,
        39 to 9,
        38 to 8,
        35..37 to 7,
        32..34 to 6,
        29..31 to 5,
        25..28 to 4,
        22..24 to 3,
        19..21 to 2,
        0..18 to 1
    )
    var el:Int = 0
    for (i in person.keys){
        el = i
    }
    var findEl:Int = 0
    for(j in marks.keys){
        if(j  ==el){
            findEl = j as Int
        }
    }
    println(marks[35..37])
}
