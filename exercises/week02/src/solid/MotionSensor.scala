package solid

/**
  * A sensor which is not battery-powered and is a security sensor (unlike SmokeSensor and FireSensor)
  */
class MotionSensor extends Sensor {
  override def isTriggered: Boolean = false

  override def getLocation: String = "unknown location"

  override def getSensorType: String = "motion sensor"

  override def getSensorCategory = Category.Security
}
