package bridge

/**
  * A locking system that locks all the doors
  */
class CentralLocking extends LockingSystem {
  override def unlock(): Unit = {
    println("Unlocking driver's side door")
    println("Unlocking passenger's side door")
    println("Unlocking rear doors")
    locked = false
  }

  override def lock(): Unit = {
    println("Locking driver's side door")
    println("Locking passenger's side door")
    println("Locking rear doors")
    locked = true
  }

  private var locked: Boolean = false

  def isLocked = locked
}

