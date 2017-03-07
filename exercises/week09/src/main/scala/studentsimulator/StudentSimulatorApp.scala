package studentsimulator

import akka.actor.{ActorSystem, Props}
import studentsimulator.TeacherProtocol.QuoteRequest

/**
  * The entry point for the student simulator example
  */
object StudentSimulatorApp extends App {
  val system = ActorSystem("UniversityMessageSystem")
  val teacher = system.actorOf(Props[TeacherActor])

  teacher ! QuoteRequest

  Thread.sleep(2000)

  system.shutdown()
}
