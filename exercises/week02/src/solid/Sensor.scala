package solid

trait Sensor {
  object Category extends Enumeration {
    val Hazard, Security = Value
  }

  def isTriggered: Boolean

  def getLocation: String

  def getSensorType: String

  // Add an additional method, with a default implementation, so that classes which already inherit from this trait
  // don't break.
  def getSensorCategory = Category.Hazard
}
