package observer

import scala.collection.mutable.ListBuffer

object TestObserver extends App {
    val subject: Subject = new CommentaryObject(ListBuffer[Observer](), "Soccer Match [2014AUG24]")

    val observer: Observer = new SMSUsers(subject, "Adam Warner [New York]")
    observer.subscribe()
    println()

    val observer2: Observer = new SMSUsers(subject, "Tim Ronney [London]")
    observer2.subscribe()

    val cObject: Commentary = subject.asInstanceOf[Commentary]
    cObject.setDesc("Welcome to live Soccer match")
    cObject.setDesc("Current score 0-0")
    println()

    observer2.unSubscribe()
    println()

    cObject.setDesc("It's a goal!!")
    cObject.setDesc("Current score 1-0")
    println()

    val observer3: Observer = new SMSUsers(subject, "Marrie [Paris]")
    observer3.subscribe()
    println()

    cObject.setDesc("It's another goal!!")
    cObject.setDesc("Half-time score 2-0")
}