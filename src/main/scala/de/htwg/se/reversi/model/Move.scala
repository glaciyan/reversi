package de.htwg.se.reversi.model

case class Move(on: Coordinate, enemies: List[Coordinate], yourEnd: Coordinate)
