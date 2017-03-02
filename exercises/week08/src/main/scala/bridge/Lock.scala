package bridge

/**
  * Represents a lock, which can be activated to lock or unlock a car
  */
abstract class Lock(lockingSystem: LockingSystem) {
  def activate(): Unit
}
