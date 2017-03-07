package studentsimulator

import akka.actor.ActorSystem
import akka.testkit.{EventFilter, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

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
      val teacher = system.actorOf(TeacherActor.props, "teacher")
      val student = system.actorOf(StudentActor.props(teacher), "student")

      EventFilter.info(pattern = "QuoteResponse", occurrences = 1) intercept {
        student ! StudentActor.InitSignal
      }
    }
  }

  override def afterAll: Unit = {
    super.afterAll
    system.shutdown()
  }

}
