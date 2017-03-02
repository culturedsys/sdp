package bridge

/**
  * A radio key system based on proximity.
  */
class RadioLock(lockingSystem: LockingSystem) extends Lock(lockingSystem) {

  import RadioLock._

  private var proximity: Proximity = Far

  /**
    * Detects whether the key is inside
    *
    * @param proximity
    */
  def detectProximity(proximity: Proximity) = this.proximity = proximity

  /**
    * Locks or unlocks the car, depending on the proximity of the key. If the key is inside, it will not do anything (to
    * avoid someone unlocking your car while you are inside it, or you locking your keys inside the car). If the key is
    * near, it will unlock or lock the car as appropriate. If they key is far away, it will only lock the car.
    */
  override def activate(): Unit = {
    println("Key proximity is " + proximity)
    if (lockingSystem.isLocked && proximity == Near)
      lockingSystem.lock()
    else if (proximity != Inside)
      lockingSystem.lock()
  }
}

object RadioLock {
  /**
    * Represents one of three proximities - either inside, near to the outside, or far away
    */
  sealed trait Proximity
  case object Inside extends Proximity
  case object Near extends Proximity
  case object Far extends Proximity
}
