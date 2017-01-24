package solid

/**
  * A trait for battery powered devices, following the principle of Interface Segregation to separate the core sensor
  * behaviour (i.e., sensing) from the battery level check
  */
trait BatteryPowered {
  def getBatteryPercentage: Double
}
