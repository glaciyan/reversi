package de.htwg.se.reversi.util

// TODO: testen
trait Observable {
  private var listeners: Vector[Observer] = Vector()
  def listenerCount: Int = listeners.size

  def add(o: Observer): Unit = listeners = listeners :+ o

  def remove(o: Observer): Unit = listeners = listeners.filterNot(_ == o)

  def notifyObservers(e: Event): Unit = listeners.foreach(_.update(e))
}
