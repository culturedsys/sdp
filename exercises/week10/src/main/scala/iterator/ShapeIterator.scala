package iterator

import java.util.Iterator

class ShapeIterator(private var shapes: Array[Shape]) extends Iterator[Shape] {

  override def hasNext(): Boolean = ???

  override def next(): Shape = ???

  override def remove(): Unit = ???
}