package de.htwg.se.reversi.views

import de.htwg.se.reversi.controller.Controller
import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.util.PutEvent.{AlreadyPlacedError, GameDone, Placed}
import de.htwg.se.reversi.util.{Observer, PutEvent}

import java.text.ParseException
import java.util.{InputMismatchException, Scanner}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

class TUIView(controller: Controller) extends GameUI, Observer {
  controller.add(this)

  override def run(): Unit = inputLoop()

  private def inputLoop(): Unit = {
    println(displayField(controller.field))
    while !controller.finished do {
      print(s"${controller.currentPlayer.renderText()} > ")
      val input = readInput()
      input match {
        case Success((row, col)) => controller.put(row, col)
        case Failure(_) => println("Invalid input please try again")
      }
    }
  }

  // TODO: testen
  private def readInput(): Try[(Int, Int)] = {
    try {
      val input = StdIn.readLine()
      val scanner = new Scanner(input)
      val values: (AnyVal, AnyVal) = (scanner.nextInt(), scanner.nextInt())
      scanner.close()

      values match {
        case (row: Int, col: Int) if row < controller.field.size => Success((row, col))
        case _ => Failure(new InputMismatchException())
      }
    } catch {
      case e: Throwable => Failure(e)
    }
  }

  override def update(e: PutEvent): Unit = e match {
    case Placed => println(displayField(controller.field))
    case AlreadyPlacedError => println("You can't replace other stones")
    case GameDone =>
  }

  private def displayField(field: Field): String = (0 until field.size).map(fieldRow(field, _)).mkString(field.eol)

  private def fieldRow(field: Field, row: Int): String = field.row(row).map(_.renderText()).mkString("")
}

// $COVERAGE-ON$