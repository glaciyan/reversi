package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.model.stone.{BlackStone, Stone, WhiteStone}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}

import de.htwg.se.reversi.controller.modules.Test.given

class PutCommandSpec extends AnyWordSpec {
  "PutCommand when not doing anything" should {
    "throw error" in {
      val controller = Controller()
      val command = PutCommand(controller, 0, 0, controller.getPossibleMoves)
      command.undoCommand() match {
        case Failure(exception) =>
        case Success(_) => fail("Test should not return Success")
      }
    }
  }
}
