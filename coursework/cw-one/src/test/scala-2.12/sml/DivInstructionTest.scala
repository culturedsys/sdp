package sml

import org.scalatest.FunSpec

class DivInstructionTest extends FunSpec with ArithmeticBehaviours with NonBranchingBehaviours {
  describe("DivInstruction") {
    it should behave like arithmeticInstruction(DivInstruction.apply, _ / _)
    it should behave like nonBranchingInstruction(DivInstruction("no label", 0, 0, 0))
  }
}
