package state

case class RoboticStandby(r: Robot) extends RoboticState {
  def walk(): Unit = {
    r.state = r.roboticOn
    r.walk()
  }
  def cook(): Unit = {
    r.state = r.roboticOn
    r.cook()
  }
  def off(): Unit = r.state = r.roboticOff
}
