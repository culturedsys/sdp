package mediator

class Valve extends Colleague {

  var mediator: MachineMediator = _

  override def setMediator(mediator: MachineMediator): Unit = this.mediator = mediator

  def open(): Unit = {
    println("Valve is open")
    println("Filling water")
  }

  def closed(): Unit = {
    println("Valve is closed")
  }

}