package de.htwg.se.reversi.util

trait Observer {
  def update(e: PutEvent): Unit
}
