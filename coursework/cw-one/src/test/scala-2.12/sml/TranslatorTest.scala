package sml

import org.scalatest.FunSpec

class TranslatorTest extends FunSpec {
  describe("Translator") {

    val initialMachine = Machine(Labels(), Vector.empty)

    it("should translate an add instruction") {
      val machine = Translator("add-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[AddInstruction])
    }

    it("should translate a sub instruction") {
      val machine = Translator("sub-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[SubInstruction])
    }

    it("should translate a mul instruction") {
      val machine = Translator("mul-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[MulInstruction])
    }

    it("should translate a div instruction") {
      val machine = Translator("div-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[DivInstruction])
    }

    it("should translate a lin instruction") {
      val machine =Translator("lin-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[LinInstruction])
    }
  }
}
