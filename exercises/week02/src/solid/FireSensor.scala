import scala.util.Random

class FireSensor extends Sensor {

  var battery = 100.0

  override def isTriggered: Boolean = {
    val random = new Random()
    battery -= 10
    random.nextInt(100) < 5
  }

  override def getLocation: String = "unknown location"

  override def getSensorType: String = "fire sensor"

  override def getBatteryPercentage: Double = battery
}
