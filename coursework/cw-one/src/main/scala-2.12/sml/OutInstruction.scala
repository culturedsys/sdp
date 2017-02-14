package sml

/**
  * Print the contents of register s1 on the Java console (using println)
  */
case class OutInstruction(label: String, s1: Int) extends Instruction(label, "out") {
  override def execute(m: Machine): Unit = {
    println(m.regs(s1))
  }
}
