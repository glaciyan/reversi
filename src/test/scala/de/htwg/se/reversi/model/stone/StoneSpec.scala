package de.htwg.se.reversi.model.stone

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec {
  "A stone with a state" should {
    "render the correct text" in {
      Stone(WhiteStone).renderText() should be("⚪")
      Stone(BlackStone).renderText() should be("⚫")
      Stone(NoStone).renderText() should be("🟩")
    }
  }
}
