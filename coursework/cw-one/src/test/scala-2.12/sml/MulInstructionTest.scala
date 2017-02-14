package sml

import org.scalatest.FunSpec

class MulInstructionTest extends FunSpec with ArithmeticBehaviours with NonBranchingBehaviours {
  describe("MulInstruction") {
    it should behave like arithmeticInstruction(MulInstruction.apply, _ * _)
    it should behave like nonBranchingInstruction(MulInstruction("no label", 0, 0, 0))
  }
}
