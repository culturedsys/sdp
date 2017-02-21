package observer

case class SMSUsers(s: Subject, identifier: String) extends Observer {
  def update(desc: String): Unit = println(s"SMS to $identifier: $desc")

  def subscribe() = s.subscribeObserver(this)

  def unSubscribe() = s.unSubscribeObserver(this)
}
