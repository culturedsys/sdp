package sml

/**
  * If the contents of register s1 is not zero, then make the statement labeled L2 the next one to execute
  */
case class BnzInstruction(label: String, opcode: String, s1: Int, l2: String) extends Instruction(label, opcode) {
  override def execute(m: Machine): Unit = {
    val value = m.regs(s1)

    if (value != 0) {
      val index = m.labels.labels.indexOf(l2)
      m.pc = index
    }
  }

  override def toString: String =
    super.toString + " branch to " + l2 + " if nonzero r" + s1 + "\n"
}

object BnzInstruction {
  def apply(label: String, s1: Int, l2: String): BnzInstruction = new BnzInstruction(label, "bnz", s1, l2)
}