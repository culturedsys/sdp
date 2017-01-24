package solid

import java.time.LocalTime

/**
  * A control unit that only polls during particular times. Implemented using ControlUnit, respecting the open/closed
  * principle: we are extending ControlUnit (open for extension) without modifying its code (closed for modification).
  */
class SecurityControlUnit(sensors: Traversable[Sensor]) extends ControlUnit(sensors) {
  import SecurityControlUnit._

  override def pollSensors(): Unit = {
    val now = LocalTime.now()
    if ( START_TIME.compareTo(now) < 0 || END_TIME.compareTo(now) > 0) {
      super.pollSensors()
    }
  }
}

object SecurityControlUnit {
  final val START_TIME = LocalTime.of(22, 0)
  final val END_TIME = LocalTime.of(6, 0)
}

