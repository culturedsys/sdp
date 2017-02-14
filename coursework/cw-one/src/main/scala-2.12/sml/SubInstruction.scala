package sml

/**
  * Subtract the contents of register s2 from the contents of s1 and store the result in register r
  */
case class SubInstruction(label: String, r: Int, s1: Int, s2: Int)
  extends ArithmeticInstruction(label, "sub", r, s1, s2, _ - _)

object SubInstruction {
  def apply(fields: Array[String]): SubInstruction =
    SubInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
}