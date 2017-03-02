package bridge

/**
  * Test the various combinations of locks and locking systems
  */
object LockingTest extends App {
  val centralLocking = new CentralLocking
  val gearLocking = new GearLocking

  var radioLock = new RadioLock(centralLocking)
  radioLock.detectProximity(RadioLock.Far)
  radioLock.activate()

  radioLock.detectProximity(RadioLock.Near)
  radioLock.activate()

  radioLock.detectProximity(RadioLock.Inside)
  radioLock.activate()

  val keyId = 10
  val keyLock = new KeyLock(gearLocking, keyId)

  keyLock.activate()
  keyLock.insertKey(keyId)
  keyLock.activate()
}
