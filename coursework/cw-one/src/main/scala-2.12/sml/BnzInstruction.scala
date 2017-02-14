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

  override def toString =
    super.toString + " branch to " + l2 + " if nonzero r" + s1 + "\n"
}

object BnzInstruction {
  def apply(fields: Array[String]): BnzInstruction =
    BnzInstruction(fields(0), fields(2).toInt, fields(3))
}
