package sml

/**
  * Divide (Java integer division) the contents of register s1 by the contents of register s2 and store the result in
  * register r
  */
case class DivInstruction(label: String, r: Int, s1: Int, s2: Int)
  extends ArithmeticInstruction(label, "div", r, s1, s2, _ / _) {
  override def toString = super.toString + " r" + s1 + " / r" + s2 + " to r" + r + "\n"
}

object DivInstruction {
  def apply(fields: Array[String]): DivInstruction =
    DivInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
}