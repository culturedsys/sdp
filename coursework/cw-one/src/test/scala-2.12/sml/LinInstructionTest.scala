package sml

import org.scalatest.FunSpec

class LinInstructionTest extends FunSpec with NonBranchingBehaviours {
  describe("LinInstruction") {
    it("should load a value into a register") {
      val machine = Machine(Labels(), Vector.empty)

      val result = 1
      val value = 20

      val instruction = LinInstruction("no label", result, value)

      instruction.execute(machine)

      assert(machine.regs(result) === value)
    }

    it should behave like nonBranchingInstruction(LinInstruction("no label", 0, 0))
  }
}
