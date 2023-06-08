package de.htwg.se.reversi.views

import de.htwg.se.reversi.controller.{Controller, InputCommand}
import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.util.{AlreadyPlacedError, EmojiNumbers, GameDone, InvalidPut, Observer, Placed, PutEvent}

import java.text.ParseException
import java.util
import java.util.{InputMismatchException, Scanner}
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

class TUIView(controller: Controller) extends GameUI, Observer {
  controller.add(this)

  override def run(): Unit = inputLoop()

  private def inputLoop(): Unit = {
    printField()

    while !controller.finished do {
      val possibleMoves = controller.field.getPossibleMoves(controller.gameState.currentPlayer, controller.gameState.nextPlayer)
      println(s"${possibleMoves.size} possible moves: ${possibleMoves.map(m => (m.on.row + 1, EmojiNumbers.convert(m.on.col + 1))).mkString(", ")}")

      print(s"${controller.currentPlayer.renderText()} > ")
      val input = readInput()
      input match {
        case Success((row: Int, col: Int)) => controller.put(row - 1, col - 1, possibleMoves)
        case Success(InputCommand.Undo) =>
          controller.undo() match {
            case Failure(_) => println("Invalid undo, put some stones down first")
            case _ =>
          }
        case Failure(_) => println("Invalid input please try again")
      }
    }
  }

  private def readInput(): Try[(Int, Int) | InputCommand] = {
    try {
      val input = StdIn.readLine()

      if (input == "undo") {
        return Success(InputCommand.Undo)
      }

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
    case Placed => printField()
    case AlreadyPlacedError => println("You can't replace other stones")
    case InvalidPut => println("You can't place your stone here")
    case GameDone(winner) =>
  }

  private def printField(): Unit = {
    println(s"  ${(1 to controller.field.size).map(EmojiNumbers.convert).mkString("")}")
    println(displayField(controller.field))
  }

  private def displayField(field: Field): String = (0 until field.size).map(i => s"${i + 1} ${fieldRow(field, i)}").mkString(field.eol)

  private def fieldRow(field: Field, row: Int): String = field.row(row).map(_.renderText()).mkString("")
}

// $COVERAGE-ON$