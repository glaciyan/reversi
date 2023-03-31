package de.htwg.se.reversi.model

enum Stone(stringRepresentation: String) {
  override def toString = stringRepresentation
  case White extends Stone("O")
  case Black extends Stone("X")
  case Nothing extends Stone(" ")
}
