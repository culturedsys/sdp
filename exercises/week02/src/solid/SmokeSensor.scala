import scala.util.Random

class SmokeSensor extends Sensor {

  var battery = 100.0

  override def isTriggered: Boolean = {
    val random = new Random()
    battery -= 20
    random.nextInt(100) < 10
  }

  override def getLocation: String = "unknown location"

  override def getSensorType: String = "smoke sensor"

  override def getBatteryPercentage: Double = battery
}
