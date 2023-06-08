package de.htwg.se.reversi.views

import de.htwg.se.reversi.controller.Controller
import de.htwg.se.reversi.model.stone.{Stone, WhiteStone}
import de.htwg.se.reversi.util.{AlreadyPlacedError, GameDone, InvalidPut, Observable, Observer, Placed, PutEvent}

import java.awt.{Color, Frame}
import scala.language.postfixOps
import scala.swing.event.MouseClicked
import scala.swing.{BorderPanel, BoxPanel, Button, Component, Dimension, FlowPanel, GridPanel, Label, MainFrame, Orientation}

class GUIView(controller: Controller) extends MainFrame, GameUI, Observer {
  var gameOver = false

  controller.add(this)

  title = "reversi"

  private val fieldPanel = new FlowPanel()
  private val statusLabel = new Label()

  contents = new BoxPanel(orientation = Orientation.Vertical) {
    contents += statusLabel
    contents += fieldPanel
  }

  reloadField()

  private def reloadField(): Unit = {
    fieldPanel.contents.clear()
    fieldPanel.contents += new GridPanel(controller.field.size, controller.field.size) {
        contents ++= (for
          r <- 0 until controller.field.size
          c <- 0 until controller.field.size
          stone <- controller.field.getStone(r, c)
        yield new GStone(r, c, stone))
      }

    normal("it's your turn")
    pack()
  }

  private def normal(s: String): Unit = {
    statusLabel.foreground = Color.BLACK
    statusLabel.text = s"${controller.gameState.currentPlayer.name} $s"
    pack()
  }
  private def warning(s: String): Unit = {
    statusLabel.foreground = Color.RED
    statusLabel.text = s"${controller.gameState.currentPlayer.name} $s"
    pack()
  }

  private def good(s: String): Unit = {
    statusLabel.foreground = Color.GREEN
    statusLabel.text = s
    pack()
  }

  private def possibleMoves() = controller.field.getPossibleMoves(controller.gameState.currentPlayer, controller.gameState.nextPlayer)

  override def run(): Unit = {
    pack()
    centerOnScreen()
    open()
  }

  override def update(e: PutEvent): Unit = e match {
    case Placed => reloadField()
    case AlreadyPlacedError => warning("this tile has already been placed")
    case GameDone(winner) =>
      gameOver = true
      good(s"${winner.name} won!")
    case InvalidPut => warning("you can't place anything here")
  }


  private class GStone(row: Int, col: Int, stone: Stone) extends Button {
    if (possibleMoves().exists(m => m.on.row == row && m.on.col == col))
      background = Color.CYAN
    else
      background = stone.renderColor()

    preferredSize = new Dimension(90, 90)


    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(src, pt, mod, clicks, props) =>
        if (!gameOver) controller.put(row, col, possibleMoves())
    }
  }
}
