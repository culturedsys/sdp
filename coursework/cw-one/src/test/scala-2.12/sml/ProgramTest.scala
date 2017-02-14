package sml

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.scalatest.FunSpec

/**
  * Execute a full program (the factorial example from the coursework specification)
  */
class ProgramTest extends FunSpec {
  final val PATH = "src/test/resources/"

  describe("A full program") {
    it("should calculate the correct answer") {
      val machine = Translator(PATH + "factorial.sml").readAndTranslate(Machine(Labels(), Vector.empty))

      val stream = new ByteArrayOutputStream()

      Console.withOut(stream) {
        machine.execute()
      }

      assert(stream.toString.startsWith("720"))
    }
  }
}
