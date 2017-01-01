/**
  * Created by А.Скрипкин on 29.12.2016.
  */
package ConwayLife

import scala.collection.immutable.HashSet

case class CellCoord(row :Int, col :Int) {
  def +(off :CellOffset) :CellCoord = CellCoord(row+off.dr, col+off.dc)
  override def hashCode(): Int =  (row+col) % 1000
}

case class CellOffset(dr :Int, dc :Int)

case class World(alives :HashSet[CellCoord]) {

  private val neighbors =  List (
    CellOffset(-1,0),
    CellOffset(1,0),
    CellOffset(0,-1),
    CellOffset(0,1),
    CellOffset(-1,-1),
    CellOffset(1,-1),
    CellOffset(1,1),
    CellOffset(-1,1)
  )

  def aliveNeighbors(crd :CellCoord) :Int = neighbors.count(off => cellState(crd + off))

  def cellState(crd :CellCoord) :Boolean = alives contains crd

  def newCellState(crd :CellCoord) : Boolean = {
    val cnt = aliveNeighbors(crd)
    if(cellState(crd)) cnt>=2 && cnt<=3 else cnt==3
  }

  def minRow :Int = if (alives.isEmpty) 0 else alives.map(_.row).min
  def maxRow: Int = if (alives.isEmpty) 0 else alives.map(_.row).max
  def minCol :Int = if (alives.isEmpty) 0 else alives.map(_.col).min
  def maxCol :Int = if (alives.isEmpty) 0 else alives.map(_.col).max

  private def cellList :List[CellCoord] = {
    (for {
      row <- minRow-1 to maxRow+2
      col <- minCol-1 to maxCol+2
    } yield CellCoord(row, col)).toList
  }

  def step() :World = World {
    HashSet(cellList.filter(newCellState):_*)
  }
}
