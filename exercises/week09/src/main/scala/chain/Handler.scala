package chain

trait Handler {
	def setNextHandler(handler: Handler)
	def process(file: File)
	def getName: String
}

abstract class AbstractHandler(name: String) extends Handler {
  var handler: Option[Handler] = None

  override def setNextHandler(handler: Handler): Unit = this.handler = Some(handler)

  override def getName: String = name

  /**
    * Actually implement the processing.
    *
    * @return true if the file was processed, false otherwise.
    */
  protected def processImpl(file: File): Boolean

  def process(file: File): Unit =
    if (!processImpl(file)) {
      handler match {
        case Some(h) =>
          println(s"$getName forwards request to ${h.getName}")
          h.process(file)
        case None => println("File not supported")
      }
    }
}
    