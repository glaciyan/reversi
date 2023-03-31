package de.htwg.se.reversi.model

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class StoneSpec extends AnyWordSpec {
  "A Stone" when {
    "white" should {
      val white = Stone.White
      "be O" in {
        white.toString should be("O")
      }
    }
  }
}
