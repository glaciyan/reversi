package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.model.stone.{BlackStone, Stone, WhiteStone}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}

class PutCommandSpec extends AnyWordSpec {
  val sampleField: Field = Field().put(3, 3, Stone(BlackStone)).put(3, 4, Stone(WhiteStone)).put(4, 3, Stone(WhiteStone)).put(4, 4, Stone(BlackStone))

  "PutCommand when not doing anything" should {
    "throw error" in {
      val controller = Controller(sampleField, WhiteStone)
      val command = PutCommand(controller, 0, 0)
      command.undoCommand() match {
        case Failure(exception) =>
        case Success(_) => fail("Test should not return Success")
      }
    }
  }
}
