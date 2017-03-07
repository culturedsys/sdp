package studentsimulator

import akka.actor.{ActorSystem, Props}
import akka.testkit.{EventFilter, TestActorRef, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec, WordSpecLike}
import studentsimulator.TeacherProtocol.{InitSignal, QuoteRequest}

/**
  * Tests for the student simulator
  */
class StudentSimulatorTest
  extends TestKit(ActorSystem("UniversityMessageSystem",
                    ConfigFactory.parseString("akka.loggers = [\"akka.testkit.TestEventListener\"]")))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  "A student" must {
    "log a quote in response to InitSignal" in {
      val teacher = system.actorOf(Props[TeacherActor], "teacher")
      val student = system.actorOf(Props(new StudentActor(teacher)), "student")

      EventFilter.info(pattern = "QuoteResponse", occurrences = 1) intercept {
        student ! InitSignal
      }
    }
  }

  override def afterAll: Unit = {
    super.afterAll
    system.shutdown()
  }

}
