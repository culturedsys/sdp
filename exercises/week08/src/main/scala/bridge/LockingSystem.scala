package bridge

/**
  * Represents a method of locking a car
  */
trait LockingSystem {
  def isLocked: Boolean
  def lock(): Unit
  def unlock(): Unit
}
