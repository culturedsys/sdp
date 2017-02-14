package sml

import org.scalatest.FunSpec

/**
  * Shared tests for instructions that do not branch
  */
trait NonBranchingBehaviours {
  this: FunSpec =>

  def nonBranchingInstruction(instruction: Instruction) {
    it("should not change the program counter") {
      val machine = Machine(Labels(), Vector.empty)

      // Set all registers to 1, which is a safer default value than 0 (executing a divide instruction when the
      // registers are 0 will cause a divide by zero error).
      (0 to 31).foreach(i => machine.regs(i) = 1)

      val prePC = machine.pc
      instruction.execute(machine)
      assert(machine.pc == prePC)
    }
  }
}
