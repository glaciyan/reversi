package de.htwg.se.reversi.util

trait Observable {
  private var listeners: Vector[Observer] = Vector()
  def listenerCount: Int = listeners.size

  def add(o: Observer): Unit = listeners = listeners :+ o

  def remove(o: Observer): Unit = listeners = listeners.filterNot(_ == o)

  def notifyObservers(e: PutEvent): Unit = listeners.foreach(_.update(e))
}
