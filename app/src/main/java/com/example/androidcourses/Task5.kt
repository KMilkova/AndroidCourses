package com.example.androidcourses

interface ChessFigureInterface{
    val boardPosition:String
    fun move()
    fun status(){
        println("On board")
    }
}

class ChessFigure:ChessFigureInterface{
    override val boardPosition = "A3"

    override fun move() {
        println("Move to $boardPosition")
    }



}

fun main(){
    val rosie:Player = Player("Rosie","master",15,1)
    println(rosie.toString())
}

//задание 5с data class для игрока
data class Player(val name:String,val rank:String,val numbersOfWins:Int, val losses:Int){
    override fun toString(): String {
        return "Name: $name, rank: $rank, numbers of wins: $numbersOfWins, losses: $losses"
    }
}
