package sml

import org.scalatest.FunSpec

/**
  * Shared behaviour for arithmetic operations
  */
trait ArithmeticBehaviours {
  this: FunSpec =>

  def arithmeticInstruction(constructor: (String, Int, Int, Int) => Instruction, op: (Int, Int) => Int) {
    it("should operate on two registers and save to a third") {
      val machine = Machine(Labels(), Vector.empty)

      val operand1 = 0
      val val1 = 10

      val operand2 = 1
      val val2 = 20

      val result = 3

      machine.regs(operand1) = val1
      machine.regs(operand2) = val2
      machine.regs(result) = 0

      val instruction = constructor("no label", result, operand1, operand2)

      instruction.execute(machine)

      assert(machine.regs(result) === op(val1, val2))
    }

    it("should add two registers and save to one of them") {
      val machine = Machine(Labels(), Vector.empty)

      val operand1 = 0
      val val1 = 10

      val operand2 = 1
      val val2 = 20

      machine.regs(operand1) = val1
      machine.regs(operand2) = val2

      val instruction = constructor("no label", operand1, operand1, operand2)

      instruction.execute(machine)

      assert(machine.regs(operand1) === op(val1, val2))
    }

    it("should add a register to itself and save to a different one") {
      val machine = Machine(Labels(), Vector.empty)

      val operand1 = 0
      val val1 = 10

      val result = 3

      machine.regs(operand1) = val1
      machine.regs(result) = 0

      val instruction = constructor("no label", result, operand1, operand1)

      instruction.execute(machine)

      assert(machine.regs(result) === op(val1, val1))
    }

    it("should add a register to itself and save in the same register") {
      val machine = Machine(Labels(), Vector.empty)

      val operand1 = 0
      val val1 = 10

      machine.regs(operand1) = val1

      val instruction = constructor("no label", operand1, operand1, operand1)

      instruction.execute(machine)

      assert(machine.regs(operand1) === op(val1, val1))
    }
  }
}
