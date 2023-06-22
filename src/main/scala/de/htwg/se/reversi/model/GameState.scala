package de.htwg.se.reversi.model

import de.htwg.se.reversi.controller.IFileSave
import de.htwg.se.reversi.model.stone.{BlackStone, NoStone, Stone, StoneState, WhiteStone}
import de.htwg.se.reversi.util.{JSONParseException, PutEvent, XMLParseException}
import play.api.libs.json.{JsValue, Json}

import java.io.File
import scala.util.{Failure, Success, Try}
import scala.xml.Node

case class GameState(currentPlayer: StoneState, field: IField) extends IGameState {
  override def put(row: Int, col: Int): IGameState = this.put(row, col, currentPlayer, nextPlayer)

  override def put(row: Int, col: Int, stone: StoneState, next: StoneState): IGameState =
    this.copy(next, field.put(row, col, Stone(stone)))

  override def nextPlayer: StoneState = currentPlayer match {
    case WhiteStone => BlackStone
    case BlackStone => WhiteStone
    case _ => WhiteStone
  }

  override def toXML: Node = <gamestate>
    <currentPlayer>
      {Stone(currentPlayer).toXML}
    </currentPlayer>{field.toXML}
  </gamestate>

  override def json: JsValue = Json.obj(
    "player" -> Stone(currentPlayer).json,
    "field" -> field.json
  )
}

object GameState extends XMLDeserializable[IGameState], JSONDeserializable[IGameState] {
  def fromXML(node: Node): Try[IGameState] = {
    val playerSeq = node \ "currentPlayer" \ "stone"
    val fieldSeq = node \ "field"

    (playerSeq.size, fieldSeq.size) match {
      case (1, 1) => (Stone.fromXML(playerSeq.head), Field.fromXML(fieldSeq.head)) match {
        case (Success(stone), Success(field)) => Success(GameState(stone.state, field))
        case (Success(_), Failure(fieldError)) => Failure(new XMLParseException(s"could not read field: ${fieldError.getMessage}"))
        case (Failure(stoneError), Failure(_)) => Failure(new XMLParseException(s"could not read player: ${stoneError.getMessage}"))
        case _ => Failure(new XMLParseException("could not read player and field"))
      }
      case _ => Failure(new XMLParseException("game state does not have a player or field"))
    }
  }

  override def fromJSON(json: JsValue): Try[IGameState] = {
    val player = (json \ "player").toOption
    val field = (json \ "field").toOption

    (player, field) match {
      case (Some(pl), Some(fld)) => (Stone.fromJSON(pl), Field.fromJSON(fld)) match {
        case (Success(stone), Success(field)) => Success(GameState(stone.state, field))
        case (Success(_), Failure(fieldError)) => Failure(new JSONParseException(s"could not read field: ${fieldError.getMessage}"))
        case (Failure(stoneError), Failure(_)) => Failure(new JSONParseException(s"could not read player: ${stoneError.getMessage}"))
        case _ => Failure(new JSONParseException("could not read player and field"))
      }
      case _ => Failure(new JSONParseException("game state does not have a player or field"))
    }
  }
}