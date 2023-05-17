package de.htwg.se.reversi.views

import de.htwg.se.reversi.controller.Controller
import de.htwg.se.reversi.util.Event.{AlreadyPlacedError, GameDone, Placed}
import de.htwg.se.reversi.util.{Event, Observer}

import java.text.ParseException
import java.util.{InputMismatchException, Scanner}
import scala.io.StdIn

class TUIView(controller: Controller) extends GameUI, Observer {
  controller.add(this)

  override def run(): Unit = inputLoop()

  def inputLoop(): Unit = {
    println(controller.field.display)
    while !controller.finished do {
      print(s"${controller.currentPlayer} > ")
      val input = readInput()
      input match {
        case Some((row, col)) => controller.put(row, col)
        case None => println("Invalid input please try again")
      }
    }
  }

  def readInput(): Option[(Int, Int)] = {
    try {
      val input = StdIn.readLine()
      val scanner = new Scanner(input)
      val values: (AnyVal, AnyVal) = (scanner.nextInt(), scanner.nextInt())
      scanner.close()

      values match {
        case (row: Int, col: Int) if row < controller.field.size => Some((row, col))
        case _ => None
      }
    } catch {
      case _: Throwable => None
    }
  }

  override def update(e: Event): Unit = e match {
    case Placed => println(controller.field.display)
    case AlreadyPlacedError => println("You can't replace other stones")
    case GameDone =>
  }
}
