package de.htwg.se.reversi.util

// TODO: testen
trait Observable {
  var listeners: Vector[Observer] = Vector()

  def add(o: Observer) = listeners = listeners :+ o

  def remove(o: Observer) = listeners = listeners.filterNot(_ == o)

  def notifyObservers(e: Event): Unit = listeners.foreach(_.update(e))
}
