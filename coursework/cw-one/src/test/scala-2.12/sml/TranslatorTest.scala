package sml

import org.scalatest.FunSpec

class TranslatorTest extends FunSpec {
  describe("Translator") {

    val initialMachine = Machine(Labels(), Vector.empty)

    it("should translate an add instruction") {
      val machine = Translator("add-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[AddInstruction])
    }

    it("should translate a lin instruction") {
      val machine =Translator("lin-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[LinInstruction])
    }
  }
}
