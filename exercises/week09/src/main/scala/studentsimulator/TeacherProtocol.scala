package studentsimulator

import scala.util.Random

import akka.actor.Actor
import studentsimulator.TeacherProtocol.{QuoteRequest, QuoteResponse}

/**
  * The protocol used to communicate with Teacher actors
  */
object TeacherProtocol {
  case class QuoteRequest()
  case class QuoteResponse(quoteString: String)
}

class TeacherActor extends Actor {

  val quotes = List(
    "A spectre is haunting Europe",
    "Everything happens twice: the first time as tragedy, the second as farce",
    "Wealth appears to be a great accumulation of commodities"
  )

  override def receive = {
    case QuoteRequest => {
      val quote = QuoteResponse(quotes(Random.nextInt(quotes.size)))

      println("Your quote: " + quote)
    }
  }
}
