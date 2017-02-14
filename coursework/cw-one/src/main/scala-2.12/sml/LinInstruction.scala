package sml

/**
  * Store integer x in register r
  */
case class LinInstruction(label: String, r: Int, x: Int) extends Instruction(label, "lin") {

  override def execute(m: Machine) =
    m.regs(r) = x

  override def toString(): String = {
    super.toString + " register " + r + " value is " + x + "\n"
  }
}