object ControlUnit

/**
  * The primary constructor of ControlUnit is used to inject the dependency, namely, the list of sensors.
  * @param sensors
  */
class ControlUnit(sensors: Traversable[Sensor]) {

  /**
    * This method checks whether sensors are triggered, and displays messages reporting their state.
    */
  def pollSensors() {
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
