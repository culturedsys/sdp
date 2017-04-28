package mediator

class Heater extends Colleague {

  var mediator: MachineMediator = _

  override def setMediator(mediator: MachineMediator): Unit = this.mediator = mediator

  var targetTemp = 20

  def on(temp: Int): Unit = {
    println("Heater is on")
    targetTemp = temp
    if (mediator.checkTemperature(temp)) {
      mediator.off()
    }
  }

  def off(): Unit = {
    println(s"Temperature is set to $targetTemp")
    println("Heater is off")
  }
}