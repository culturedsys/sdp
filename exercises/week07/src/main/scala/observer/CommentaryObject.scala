package observer

import scala.collection.mutable.ListBuffer

class CommentaryObject(var subscribers: ListBuffer[Observer], val title: String) extends Subject with Commentary {
  def subscribeObserver(observer: Observer): Unit = subscribers += observer

  def unSubscribeObserver(observer: Observer): Unit = {
    val index = subscribers.indexOf(observer)
    subscribers.remove(index)
  }

  def notifyObservers() =
    if (subjectDetails != null)
      subscribers.foreach(_.update(subjectDetails))

  var subjectDetails: String = null

  override def setDesc(desc: String): Unit = {
    subjectDetails = desc
    notifyObservers()
  }
}
