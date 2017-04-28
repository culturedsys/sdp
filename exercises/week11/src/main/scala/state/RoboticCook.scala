package state

case class RoboticCook(r: Robot) extends RoboticState {
  def walk(): Unit = println("Walking...")
  def cook(): Unit = println("Cooking...")
  def off(): Unit = println("Cannot switch off in cooking state")
}
