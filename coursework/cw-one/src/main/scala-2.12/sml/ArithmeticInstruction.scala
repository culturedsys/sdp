package sml

/**
  * Common base class for arithmetic instructions
  *
  * @param label    the label of this instruction
  * @param opcode   the name of the opcode this instruction represents
  * @param result   the register number in which to store the result
  * @param operand1 the register number from which to read the left operand
  * @param operand2 the register number from which to read the right operandf
  * @param operation  the operation to apply to the two operands
  */
class ArithmeticInstruction(label: String, opcode: String,
                            result: Int, operand1: Int, operand2: Int,
                            operation: (Int, Int) => Int)
  extends Instruction(label, opcode) {

  /**
    * Apply the supplied operation to the values in the given operand registers, and store the result in the given
    * result register
    *
    * @param m
    */
  override def execute(m: Machine) {
    val value1 = m.regs(operand1)
    val value2 = m.regs(operand2)
    m.regs(result) = operation(value1, value2)
  }

  override def toString(): String = {
    super.toString + " " + operand1 + " + " + operand2 + " to " + result
  }
}
