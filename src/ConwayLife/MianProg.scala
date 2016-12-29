package ConwayLife

/**
  * Created by А.Скрипкин on 29.12.2016.
  */
object MianProg extends App {

  val initial = List(CellCoord(0,0), CellCoord(0,1), CellCoord(0,2))
  val w = World(initial).step().step().step()

}
