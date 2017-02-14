package sml

/**
  * Subtract the contents of register s2 from the contents of s1 and store the result in register r
  */
case class SubInstruction(label: String, r: Int, s1: Int, s2: Int)
  extends ArithmeticInstruction(label, "sub", r, s1, s2, _ - _)
