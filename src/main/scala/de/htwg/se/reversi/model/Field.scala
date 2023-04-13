package de.htwg.se.reversi.model

import de.htwg.se.reversi.model
import de.htwg.se.reversi.model.Main.spielfeld

// TUI Spielfeld Klasse

class TuiSpielfeld(size: Int) {
  private val spielfeld = Array.ofDim[Char](size, size)

  // Spielfeld initialisieren
  def init(): Unit = {
    for (i <- 0 until size; j <- 0 until size) {
      spielfeld(i)(j) = '.'
    }
  }

  // Spielfeld anzeigen
  def display(): String = {
    val sb = new StringBuilder
    sb.append("Spielfeld:\n")
    for (i <- 0 until size) {
      for (j <- 0 until size) {
        sb.append(s"${spielfeld(i)(j)} ")
      }
      sb.append("\n")
    }
    sb.toString()
  }

  // Spielfeld aktualisieren
  def update(x: Int, y: Int, value: Char): Unit = {
    spielfeld(x)(y) = value
  }
}

// Beispielverwendung
object Main extends App {
  val size = 8
  val spielfeld = new TuiSpielfeld(size)
  spielfeld.init()
  spielfeld.update(1, 1, 'X')
  spielfeld.update(3, 4, 'O')
  print(spielfeld.display())    //Spielfeld auf der Konsole ausgeben
}