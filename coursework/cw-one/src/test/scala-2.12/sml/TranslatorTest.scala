package sml

import org.scalatest.FunSpec

class TranslatorTest extends FunSpec {
  final val PATH = "src/test/resources/"

  describe("Translator") {

    val initialMachine = Machine(Labels(), Vector.empty)

    it("should translate an add instruction") {
      val machine = Translator(PATH + "add-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[AddInstruction])
    }

    it("should translate a sub instruction") {
      val machine = Translator(PATH + "sub-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[SubInstruction])
    }

    it("should translate a mul instruction") {
      val machine = Translator(PATH + "mul-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[MulInstruction])
    }

    it("should translate a div instruction") {
      val machine = Translator(PATH + "div-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[DivInstruction])
    }

    it("should translate a lin instruction") {
      val machine = Translator(PATH + "lin-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[LinInstruction])
    }

    it("should translate an out instruction") {
      val machine = Translator(PATH + "out-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[OutInstruction])
    }

    it("should translate a bnz instruction") {
      val machine = Translator(PATH + "bnz-instruction.sml").readAndTranslate(initialMachine)

      assert(machine.prog(0).isInstanceOf[BnzInstruction])
    }
  }
}
