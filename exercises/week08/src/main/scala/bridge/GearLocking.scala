package bridge

/**
  * A system that locks the gears of a car.
  */
class GearLocking extends LockingSystem {
  private var locked: Boolean = false

  override def isLocked = locked

  override def unlock(): Unit = {
    println("Unlocking the gears")
    locked = false
  }

  override def lock(): Unit = {
    println("Locking the gears")
    locked = true
  }
}
