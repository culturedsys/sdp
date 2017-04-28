package mediator

class Machine extends Colleague {

  var mediator: MachineMediator = _

  override def setMediator(mediator: MachineMediator): Unit = this.mediator = mediator

  def start(): Unit = {
    mediator.open()
    mediator.closed()
    mediator.on()
    mediator.wash()
  }
}