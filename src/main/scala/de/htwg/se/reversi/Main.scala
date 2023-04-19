package de.htwg.se.reversi

import de.htwg.se.reversi.model.{Field, Stone}

import scala.io.StdIn.{readLine, readf, readf3}

@main def main(): Unit =
  var field = Field().put(5, 4, Stone.White)
  var continue = true

  while continue do {
    println(field.display)
    print("player> ")
    val input = readLine()
    input match {
      case "q" => continue = false
      case _ =>
        val chars = input.toCharArray
        val stone = chars(0) match
          case 'X' => Stone.Black
          case 'x' => Stone.Black
          case 'O' => Stone.White
          case 'o' => Stone.White
          case _ => Stone.Nothing

        val x = chars(1).toString.toInt
        val y = chars(2).toString.toInt

        field = field.put(y, x, stone)
    }
  }

def findWinner(field: Field): Stone = field.m.row(0) match {
  case Stone.White :: Stone.White :: Stone.White :: _ => Stone.White
  case Stone.White :: Stone.White :: Stone.White :: _ => Stone.White
}
