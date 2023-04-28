package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{Field, Stone}
import de.htwg.se.reversi.util.Event.{AlreadyPlacedError, Placed}
import de.htwg.se.reversi.util.{Event, Observer}
import de.htwg.se.reversi.views.TUIView
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec {
  val sampleField: Field = Field().put(3, 3, Stone.Black).put(3, 4, Stone.White).put(4, 3, Stone.White).put(4, 4, Stone.Black)

  class TestObserver(var status: Event) extends Observer {
    override def update(e: Event): Unit = status = e
  }

  "A Controller" when {
    "created" should {
      "have a stone put" in {
        val controller = Controller(sampleField, Stone.White)
        controller.put(0, 0)
        controller.field.getStone(0, 0) should be(Stone.White)
      }
      "add,remove and notify observers" in {

        val controller = Controller(sampleField, Stone.White)
        val observer = TestObserver(Placed)
        controller.add(observer)
        controller.listeners.size should be(1)

        controller.notifyObservers(AlreadyPlacedError)
        observer.status should be(AlreadyPlacedError)

        controller.remove(observer)
        controller.notifyObservers(Placed)
        observer.status should be(AlreadyPlacedError)
      }
    }
    "with weird stating values" should {
      "have a good handle" in {
        val controller = Controller(sampleField, Stone.Nothing)
        // TODO: add observer
        controller.put(0, 0)
        controller.currentPlayer should be(Stone.White)
      }
      "handle already placed stones" in {
        val controller = Controller(sampleField, Stone.Nothing)
        controller.put(0, 0)
        controller.put(0, 0)
        controller.currentPlayer should be(Stone.White)
      }
    }
  }
}
