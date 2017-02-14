package sml

case class SubInstruction(label: String, result: Int, operand1: Int, operand2: Int)
  extends ArithmeticInstruction(label, "sub", result, operand1, operand2, _ - _)
