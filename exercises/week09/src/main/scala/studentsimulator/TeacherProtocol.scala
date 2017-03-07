package studentsimulator

import scala.util.Random
import akka.actor.{Actor, ActorLogging, ActorRef, Props}


/**
  * A teacher that can respond with a quote in response to a request
  */
class TeacherActor extends Actor with ActorLogging {

  import TeacherActor._
  import StudentActor.QuoteResponse

  val quotes = List(
    "A spectre is haunting Europe",
    "Everything happens twice: the first time as tragedy, the second as farce",
    "Wealth appears to be a great accumulation of commodities"
  )

  override def receive = {
    case QuoteRequest => {
      val quote = QuoteResponse(quotes(Random.nextInt(quotes.size)))
      sender ! quote
    }
  }
}

object TeacherActor {
  /**
    * A message representing a request for a quote
    */
  case object QuoteRequest

  /**
    * Return a props object for creating a TeacherActor.
    */
  def props: Props = Props[TeacherActor]
}

/**
  *A student who can process responses from teachers.
  *
  * @param teacher a reference to a teacher actor.
  */
class StudentActor(teacher: ActorRef) extends Actor with ActorLogging {
  import StudentActor._

  import TeacherActor.QuoteRequest

  override def receive = {
    case InitSignal =>
      teacher ! QuoteRequest
    case QuoteResponse(quote) => {
      log.info(s"Received QuoteResponse: ${quote}")
    }
  }
}

object StudentActor {

  /**
    * A message sent to tell the student actor to start running.
    */
  case object InitSignal

  /**
    * A message sent in response to a quote request.
    *
    * @param quote  the text of a quote.
    */
  case class QuoteResponse(quote: String)

  /**
    * Return Props for creating a StudentActor.
    *
    * @param teacher  a reference to a teacher actor with which this student will communicate.
    */
  def props(teacher: ActorRef) = Props(new StudentActor(teacher))
}
