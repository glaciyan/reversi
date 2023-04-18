package de.htwg.se.reversi

import de.htwg.se.reversi.model.{Field, Stone}

@main def main(): Unit =
  val field = Field().put(5,4,Stone.White)
  println(field.display)

