package sml

import org.scalatest.FunSpec

class AddInstructionTest extends FunSpec with ArithmeticBehaviours with NonBranchingBehaviours {
  describe("AddInstruction") {
    it should behave like arithmeticInstruction(AddInstruction.apply, _ + _)
    it should behave like nonBranchingInstruction(AddInstruction("no label", 0, 0, 0))
  }
}
