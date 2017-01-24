import scala.collection.mutable.ListBuffer

object ControlUnit

class ControlUnit {
  /**
    * This method creates sensors, checks whether they are triggered, and displays messages reporting their state.
    */
  def pollSensors() {
    val sensors = new ListBuffer[Sensor]()
    sensors += new FireSensor()
    sensors += new SmokeSensor()
    for (sensor <- sensors) {
      if (sensor.isTriggered) {
        System.out.println("A " + sensor.getSensorType + " sensor was triggered at " + sensor.getLocation)
      }
      else {
        System.out.println("Polled " + sensor.getSensorType + " at " + sensor.getLocation + " successfully")
      }
    }
  }
}
