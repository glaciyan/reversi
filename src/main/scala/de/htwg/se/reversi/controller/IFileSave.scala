package de.htwg.se.reversi.controller

import de.htwg.se.reversi.model.IGameState

import java.io.File
import scala.util.Try

trait IFileSave {
  def write(file: File, gameState: IGameState): Try[Unit]
  def read(file: File): Try[IGameState]
}
