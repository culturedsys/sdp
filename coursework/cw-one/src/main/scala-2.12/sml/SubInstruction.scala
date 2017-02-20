package sml

/**
  * Subtract the contents of register s2 from the contents of s1 and store the result in register r
  */
case class SubInstruction(label: String, opcode: String, r: Int, s1: Int, s2: Int)
  extends ArithmeticInstruction(label, opcode, r, s1, s2, _ - _) {
  override def toString = super.toString + " r" + s1 + " - r" + s2 + " to r" + r + "\n"
}

object SubInstruction {
  def apply(label: String, r: Int, s1: Int, s2: Int): SubInstruction = new SubInstruction(label, "sub", r, s1, s2)
}