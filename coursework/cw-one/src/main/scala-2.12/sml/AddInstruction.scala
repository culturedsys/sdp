package sml

/**
  * Add the contents of registers s1 and s2 and store the result in register r
  */
case class AddInstruction(label: String, r: Int, s1: Int, s2: Int)
  extends ArithmeticInstruction(label, "add", r, s1, s2, _ + _) {
  override def toString = super.toString + " r" + s1 + " + r" + s2 + " to r" + r + "\n"
}

object AddInstruction {
  def apply(fields: Array[String]): AddInstruction =
    AddInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
}