package ConwayLife

import scala.collection.immutable.HashSet

/**
  * Created by А.Скрипкин on 29.12.2016.
  */
object MainProg extends App {

  val initial = HashSet(CellCoord(0,0), CellCoord(0,1), CellCoord(0,2))
  val w = World(initial)

  def steps(w :World, times :Int, f: World=>Unit) :Unit = {
    if(times>0) {
      f(w)
      steps(w.step(), times-1, f)
    }
  }

  steps(w, 10, println)
}
