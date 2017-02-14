package sml

/**
  * Multiply the contents of registers s1 and s2 and store the result in register r
  */
case class MulInstruction(label: String, r: Int, s1: Int, s2: Int)
  extends ArithmeticInstruction(label, "mul", r, s1, s2, _ * _) {
  override def toString = super.toString + " r" + s1 + " * r" + s2 + " to r" + r + "\n"
}

object MulInstruction {
  def apply(fields: Array[String]): MulInstruction =
    MulInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
}