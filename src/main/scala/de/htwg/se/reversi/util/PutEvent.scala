package de.htwg.se.reversi.util

enum PutEvent {
  case Placed
  case AlreadyPlacedError
  case GameDone
  case InvalidPut
}