package sml

case class SubInstruction(label: String, result: Int, operand1: Int, operand2: Int) extends Instruction(label, "sub") {
  override def execute(m: Machine): Unit = ???
}
