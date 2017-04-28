package iterator

object TestIteratorPattern extends App {
    val storage = new ShapeStorage()
    storage.addShape("Polygon")
    storage.addShape("Hexagon")
    storage.addShape("Circle")
    storage.addShape("Rectangle")
    storage.addShape("Square")

    var iterator = storage.iterator()

    while (iterator.hasNext) println(iterator.next())

    println("Apply removing while iterating...")
    iterator = storage.iterator()

    while (iterator.hasNext) {
      println(iterator.next())
      iterator.remove()
    }
}