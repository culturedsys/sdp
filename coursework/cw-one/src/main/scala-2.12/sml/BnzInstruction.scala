package sml

/**
  * If the contents of register s1 is not zero, then make the statement labeled L2 the next one to execute
  */
case class BnzInstruction(label: String, s1: Int, l2: String) extends Instruction(label, "bnz") {
  override def execute(m: Machine): Unit = {
    val value = m.regs(s1)

    if (value != 0) {
      val index = m.labels.labels.indexOf(l2)
      m.pc = index
    }
  }
}
