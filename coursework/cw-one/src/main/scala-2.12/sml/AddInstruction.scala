package sml

class AddInstruction(label: String, op: String, val result: Int, val op1: Int, val op2: Int)
  extends ArithmeticInstruction(label, op, result, op1, op2, _ + _)

object AddInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) =
    new AddInstruction(label, "add", result, op1, op2)
}