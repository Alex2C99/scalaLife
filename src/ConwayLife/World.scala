package ConwayLife

/**
  * Created by А.Скрипкин on 29.12.2016.
  */
case class World(alives :List[CellCoord]) {

  private val neigbours =  List {
    CellOffset(-1,0)
    CellOffset(1,0)
    CellOffset(0,-1)
    CellOffset(0,1)
  }

  def aliveNeigbours(row :Int, col :Int) :Int = neigbours.count(off => cellState(CellCoord(row,col)+off)==Alive)

  def cellState(row :Int, col :Int) :Cell = if (alives contains CellCoord(row, col)) Alive else Dead
  def cellState(crd :CellCoord) :Cell = cellState(crd.row, crd.col)

  def newCellState(row :Int, col :Int) :Cell = {
    val cnt = aliveNeigbours(row, col)
    cellState(row, col) match {
      case Alive => if (cnt<2 || cnt>3) Dead else Alive
      case Dead => if (cnt<2) Dead else Alive
    }
  }

  def minRow :Int = alives.map(_.row).fold(Int.MaxValue) { Math.min }
  def maxRow: Int = alives.map(_.row).fold(Int.MinValue) { Math.max }
  def minCol :Int = alives.map(_.col).fold(Int.MaxValue) { Math.min }
  def maxCol :Int = alives.map(_.col).fold(Int.MinValue) { Math.max }

  def topLeft :CellCoord = CellCoord(minRow-1, minCol-1)
  def bottomRight :CellCoord = CellCoord(maxRow+1, maxCol+1)

  def step() :World = World {
    for {
      row <- List.range(minRow-1,maxRow+1)
      col <- List.range(minCol-1,maxCol+1)
      if Alive == newCellState(row, col)
    } yield CellCoord(row, col)
  }
}
