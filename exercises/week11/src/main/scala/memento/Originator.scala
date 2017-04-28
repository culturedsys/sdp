package memento

case class Originator(
  var x: Double,
  var y: Double,
  var careTaker: CareTaker
) {

  private var lastUndoSavepoint: String = _

  createSavepoint("INITIAL")

  def createSavepoint(savepointName: String): Unit = {
    println(s"Saving state $savepointName")
    careTaker.saveMemento(Memento(x, y), savepointName)
    lastUndoSavepoint = savepointName
  }

  def undo(): Unit = undo(lastUndoSavepoint)

  def undo(savepointName: String): Unit = setOriginatorState(savepointName)

  def undoAll(): Unit = undo("INITIAL")

  private def setOriginatorState(savepointName: String): Unit = {
    val Memento(x, y) = careTaker.getMemento(savepointName)
    this.x = x
    this.y = y
  }

  override def toString(): String = "X: " + x + ", Y: " + y
}
