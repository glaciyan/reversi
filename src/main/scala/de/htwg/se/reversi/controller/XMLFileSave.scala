package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.{GameState, IGameState, XMLDeserializable}

import scala.util.{Success, Try}
import java.io.File
import scala.xml.XML

class XMLFileSave(using deserializer: XMLDeserializable[IGameState]) extends IFileSave {
  override def write(file: File, gameState: IGameState): Try[Unit] = {
    val state = gameState.toXML
    XML.save(file.getAbsolutePath, state)
    Success({})
  }

  override def read(file: File): Try[IGameState] = {
    val gameState = XML.loadFile(file)
    deserializer.fromXML(gameState)
  }
}
