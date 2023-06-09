package de.htwg.se.reversi.views

import de.htwg.se.reversi.controller.{Controller, IController}
import de.htwg.se.reversi.model.stone.{Stone, WhiteStone}
import de.htwg.se.reversi.util.*

import java.awt.{Color, Frame}
import scala.language.postfixOps
import scala.swing.event.MouseClicked
import scala.swing.*
import scala.swing.FileChooser.Result
import scala.util.{Failure, Success}


class GUIView(using controller: IController) extends MainFrame, GameUI, Observer {
  private var gameOver = false

  controller.add(this)

  title = "reversi"

  private val fieldPanel = new FlowPanel()
  private val statusLabel = new Label()

  contents = new BoxPanel(orientation = Orientation.Vertical) {
    contents += statusLabel
    contents += fieldPanel
  }

  player("it's your turn")
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

    menuBar = new MenuBar {
      contents += new Menu("Edit") {
        contents += new MenuItem(Action("Undo") {
          gameOver = false
          controller.undo()
        }) {
          enabled = controller.canUndo
          if (enabled) text = s"Undo ${controller.getLastCommand.actionName()}"
        }
        contents += new MenuItem(Action("Redo") {
          controller.redo()
        }) {
          enabled = controller.canRedo
        }
        contents += new MenuItem(Action("Reset game") {
          while (controller.canUndo) controller.undo()
        })
        contents += new Separator()
        contents += new MenuItem(Action("Save to file...") {
          val picker = FileChooser()
          picker.showSaveDialog(this) match
            case Result.Approve => controller.saveToFile(picker.selectedFile)
            case Result.Cancel =>
            case Result.Error =>
        })
        contents += new MenuItem(Action("Load save file...") {
          val picker = FileChooser()
          picker.showOpenDialog(this) match
            case Result.Approve => controller.readFromFile(picker.selectedFile) match {
              case Failure(exception) => println(exception)
              case Success(value) =>
            }
            case Result.Cancel =>
            case Result.Error =>
        })
      }
    }

    pack()
  }

  private def player(s: String): Unit = {
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
    case Placed =>
      player("it's your turn")
      reloadField()
    case AlreadyPlacedError => warning("this tile has already been placed")
    case GameDone(winner) =>
      gameOver = true
      good(s"${winner.name} won!")
      reloadField()
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
        if (!gameOver) controller.put(row, col)
    }
  }
}
