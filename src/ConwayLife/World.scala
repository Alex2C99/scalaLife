package ConwayLife

import scala.collection.immutable.HashSet


/**
  * Created by А.Скрипкин on 29.12.2016.
  */
case class World(alives :HashSet[CellCoord]) {

  val neighbors =  List (
    CellOffset(-1,0),
    CellOffset(1,0),
    CellOffset(0,-1),
    CellOffset(0,1),
    CellOffset(-1,-1),
    CellOffset(1,-1),
    CellOffset(1,1),
    CellOffset(-1,1)
  )

  def aliveNeighbors(row :Int, col :Int) :Int = neighbors.count(off => cellState(CellCoord(row,col)+off)==Alive)

  def cellState(row :Int, col :Int) :Cell = if (alives contains CellCoord(row, col)) Alive else Dead
  def cellState(crd :CellCoord) :Cell = cellState(crd.row, crd.col)

  def newCellState(row :Int, col :Int) :Cell = {
    val cnt = aliveNeighbors(row, col)
    cellState(row, col) match {
      case Alive => if (cnt<2 || cnt>3) Dead else Alive
      case Dead => if (cnt==3) Alive else Dead
    }
  }

  def minRow :Int = if (alives.isEmpty) 0 else alives.map(_.row).min
  def maxRow: Int = if (alives.isEmpty) 0 else alives.map(_.row).max
  def minCol :Int = if (alives.isEmpty) 0 else alives.map(_.col).min
  def maxCol :Int = if (alives.isEmpty) 0 else alives.map(_.col).max

  def topLeft :CellCoord = CellCoord(minRow-1, minCol-1)
  def bottomRight :CellCoord = CellCoord(maxRow+1, maxCol+1)

  def step() :World = World {
    HashSet(
      (for {
      row <- minRow-1 to maxRow+2
      col <- minCol-1 to maxCol+2
      if Alive == newCellState(row, col)
    } yield CellCoord(row, col)).toList:_*)
  }
}
