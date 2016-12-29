package ConwayLife

import scala.collection.immutable.HashSet

/**
  * Created by А.Скрипкин on 29.12.2016.
  */
object MainProg extends App {

  val initial = HashSet(CellCoord(0,0), CellCoord(0,1), CellCoord(0,2))
  val w = World(initial)
}
