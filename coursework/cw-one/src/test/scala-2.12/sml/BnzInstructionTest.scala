package sml

import org.scalatest.FunSpec

class BnzInstructionTest extends FunSpec {
  describe("BnzInstruction") {
    it("should not alter the PC when the register is 0") {
      val machine = Machine(Labels(), Vector.empty)

      machine.regs(0) = 0

      val instruction = BnzInstruction("no label", 0, "somelabel")

      val prePC = machine.pc
      instruction.execute(machine)

      assert(machine.pc === prePC)
    }

    it("should change PC to label index when the register is not 0") {

      val target = "target"

      val labels = Labels()
      labels.add("first") // 0
      labels.add(target)  // 1

      val machine = Machine(labels, Vector.empty)

      machine.regs(0) = 10

      val instruction = BnzInstruction("no label", 0, target)

      instruction.execute(machine)

      assert(machine.pc === 1)
    }
  }
}
