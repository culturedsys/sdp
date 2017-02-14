package sml

import org.scalatest.FunSpec

class SubInstructionTest extends FunSpec with ArithmeticBehaviours with NonBranchingBehaviours {
  describe("SubInstruction") {
    it should behave like arithmeticInstruction(SubInstruction.apply, _ - _)
  }
}
