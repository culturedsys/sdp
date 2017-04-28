package iterator

import java.util.Iterator

class ShapeStorage {
  import ShapeStorage.ShapeIterator

  private var shapes = new Array[Shape](5)

  private var index: Int = 0

  def addShape(name: String): Unit = {
    shapes(index) = new Shape(index, name)
    index += 1
  }

  def iterator(): Iterator[Shape] = new ShapeIterator(this)
}

object ShapeStorage {
  private class ShapeIterator(var storage: ShapeStorage) extends Iterator[Shape] {

    private var index: Int = 0

    override def hasNext: Boolean = index < storage.shapes.length

    override def next(): Shape = {
      index += 1
      storage.shapes(index - 1)
    }

    override def remove(): Unit = {
      val newShapes = new Array[Shape](storage.shapes.length - 1)
      var j = 0

      for (i <- 0 until storage.shapes.length) {
        if (i != index - 1) {
          newShapes(j) = storage.shapes(i)
          j += 1
        }
      }

      storage.shapes = newShapes

      // The line below is necessary to match the behaviour specified by the java.lang.Iterator documentation. However,
      // the worksheet specifies a different behaviour, in which remove also advances the iterator (effectively skipping
      // one element), so I've commented this out here.
      // index -= 1
    }
  }
}