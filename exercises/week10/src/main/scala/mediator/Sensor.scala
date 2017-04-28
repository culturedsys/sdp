package mediator

class Sensor {
  def checkTemperature(temp: Int): Boolean = {
    println(s"Temperator reached $temp C")
    true
  }
}