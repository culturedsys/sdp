package sml

/**
  * Print the contents of register s1 on the Java console (using println)
  */
case class OutInstruction(label: String, s1: Int) extends Instruction(label, "out") {
  override def execute(m: Machine): Unit = {
    println(m.regs(s1))
  }

  override def toString =
    super.toString + " output r" + s1 + "\n"
}

object OutInstruction {
  def apply(fields: Array[String]): OutInstruction =
    OutInstruction(fields(0), fields(2).toInt)
}
