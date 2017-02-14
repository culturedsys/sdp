package sml

/**
  * Divide (Java integer division) the contents of register s1 by the contents of register s2 and store the result in
  * register r
  */
case class DivInstruction(label: String, r: Int, s1: Int, s2: Int)
  extends ArithmeticInstruction(label, "div", r, s1, s2, _ / _)
