package sml

/**
  * Add the contents of registers s1 and s2 and store the result in register r
  */
case class AddInstruction(label: String, r: Int, s1: Int, s2: Int)
  extends ArithmeticInstruction(label, "add", r, s1, s2, _ + _)
