package bridge


/**
  * A lock that uses a key, that locks or unlocks if the right key is inserted. An int (the identifier of
  * a particular key) is supplied when the lock is created.
  */
class KeyLock(lockingSystem: LockingSystem, key: Int) extends Lock(lockingSystem) {
  private var keyInserted = 0

  def insertKey(keyInserted: Int) = this.keyInserted = keyInserted

  override def activate(): Unit =
    if (keyInserted == key) {
      println("Correct key inserted")
      if (lockingSystem.isLocked)
        lockingSystem.unlock()
      else
        lockingSystem.lock()
    } else
      println("No correct key inserted")
}
