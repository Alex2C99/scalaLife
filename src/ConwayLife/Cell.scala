package ConwayLife

/**
  * Created by А.Скрипкин on 29.12.2016.
  */
trait Cell
case object Dead extends Cell
case object Alive extends Cell

case class CellCoord(row :Int, col :Int) {
  def +(off :CellOffset) :CellCoord = CellCoord(row+off.dr, col+off.dc)
}

case class CellOffset(dr :Int, dc :Int)
