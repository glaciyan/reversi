package de.htwg.se.reversi.util

import de.htwg.se.reversi.model.stone.Stone

trait PutEvent {

}

object Placed extends PutEvent

object AlreadyPlacedError extends PutEvent

case class GameDone(winner: Stone) extends PutEvent

object InvalidPut extends PutEvent
