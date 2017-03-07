package studentsimulator

import akka.actor.ActorSystem
import akka.testkit.{EventFilter, TestActorRef, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec, WordSpecLike}
import studentsimulator.TeacherProtocol.QuoteRequest

/**
  * Tests for the student simulator
  */
class StudentSimulatorTest
  extends TestKit(ActorSystem("UniversityMessageSystem",
                    ConfigFactory.parseString("akka.loggers = [\"akka.testkit.TestEventListener\"]")))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  "A teacher" must {
    "log a quote in response to a QuoteRequest" in {
      val teacher = TestActorRef[TeacherActor]

      EventFilter.info(pattern = "QuoteResponse", occurrences = 1) intercept {
        teacher ! QuoteRequest
      }
    }
  }

  override def afterAll: Unit = {
    super.afterAll
    system.shutdown()
  }

}
