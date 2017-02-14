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

      val prePC = machine.pc
      instruction.execute(machine)
      assert(machine.pc == prePC)
    }
  }
}
