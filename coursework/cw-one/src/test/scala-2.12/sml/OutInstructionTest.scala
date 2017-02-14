package sml

import org.scalatest.FunSpec

class OutInstructionTest extends FunSpec with NonBranchingBehaviours {
  describe("OutInstruction") {
    it("should print the value of the register") {

      val value = 10;

      val machine = Machine(Labels(), Vector.empty)

      machine.regs(0) = value

      val instruction = OutInstruction("no label", 0)

      val stream = new java.io.ByteArrayOutputStream()
      Console.withOut(stream) {
        instruction.execute(machine)
      }

      assert(stream.toString.startsWith(value.toString))
    }

    it should behave like nonBranchingInstruction(OutInstruction("no label", 0))
  }

}
