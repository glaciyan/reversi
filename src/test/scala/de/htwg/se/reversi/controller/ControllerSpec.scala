package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.Field
import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.PutEvent.{AlreadyPlacedError, Placed}
import de.htwg.se.reversi.util.{PutEvent, Observer}
import de.htwg.se.reversi.views.TUIView
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec {
  val sampleField: Field = Field().put(3, 3, Stone(BlackStone)).put(3, 4, Stone(WhiteStone)).put(4, 3, Stone(WhiteStone)).put(4, 4, Stone(BlackStone))

  class TestObserver(var status: PutEvent) extends Observer {
    override def update(e: PutEvent): Unit = status = e
  }

  "A Controller" when {
    "created" should {
      "have a stone put" in {
        val controller = Controller(sampleField, WhiteStone)
        controller.put(0, 0)
        controller.field.getStone(0, 0).state should be(WhiteStone)
      }
      "add,remove and notify observers" in {

        val controller = Controller(sampleField, WhiteStone)
        val observer = TestObserver(Placed)
        controller.add(observer)
        controller.listenerCount should be(1)

        controller.notifyObservers(AlreadyPlacedError)
        observer.status should be(AlreadyPlacedError)

        controller.remove(observer)
        controller.notifyObservers(Placed)
        observer.status should be(AlreadyPlacedError)
      }
    }
    "when a stone is already placed" should {
      "report" in {
        val controller = Controller(sampleField, WhiteStone)
        val observer = TestObserver(Placed)
        controller.add(observer)

        controller.put(3,3)
        observer.status should be (AlreadyPlacedError)
      }
    }
    "with weird stating values" should {
      "have a good handle" in {
        val controller = Controller(sampleField, NoStone)
        // TODO: add observer
        controller.put(0, 0)
        controller.currentPlayer should be(WhiteStone)
      }
      "handle already placed stones" in {
        val controller = Controller(sampleField, NoStone)
        controller.put(0, 0)
        controller.put(1, 0)
        controller.put(2, 0)
        controller.currentPlayer should be(WhiteStone)
      }
    }
  }
}
