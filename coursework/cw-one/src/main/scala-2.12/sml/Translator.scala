package sml

import java.lang.reflect.Modifier

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
class Translator(fileName: String) {
  private final val ADD = "add"
  private final val LIN = "lin"
  private final val BNZ = "bnz"
  private final val MUL = "mul"
  private final val DIV = "div"
  private final val SUB = "sub"
  private final val OUT = "out"

  /**
    * Translate an instruction name, as used in the input, to a class name. Note that this always produces
    * fully-qualified names in the "sml" package.
    *
    * @param instruction  the instruction name
    */
  def classNameForInstruction(instruction: String): String =
    "sml." + instruction(0).toUpper + instruction.substring(1) + "Instruction"

  /**
    * Create an instance of the correct instruction type from the supplied fields.
    *
    * Uses reflection to find the class to use. This class must have a static "apply" method that takes an array of
    * Strings and returns an Instruction
    *
    * @param fields an array of strings representing the elements of the instruction line in the source
    * @return Some instance of the appropriate instruction type, or None if the class doesn't exist, or doesn't have a
    *         static "apply" method taking an array of Strings
    */
  def createInstruction(fields: Array[String]): Option[Instruction] = {
    try {
      val c = Class.forName(classNameForInstruction(fields(1)))
      val m = c.getMethod("apply", Array("").getClass)

      if ((m.getModifiers & Modifier.STATIC) == 0)
        None
      else
        // As the method is static, we can safely call it on a null object
        Some(m.invoke(null, fields).asInstanceOf[Instruction])

    } catch {
      case _: ClassNotFoundException => None
      case _: NoSuchMethodException => None
    }
  }

  /**
    * translate the small program in the file into lab (the labels) and prog (the program)
    */
  def readAndTranslate(m: Machine): Machine = {
    val labels = m.labels
    var program = m.prog
    import scala.io.Source
    val lines = Source.fromFile(fileName).getLines
    for (line <- lines) {
      val fields = line.split(" ")

      if (!fields.isEmpty) {
        labels.add(fields(0))

        createInstruction(fields) match {
          case Some(instruction) => program = program :+ instruction
          case None => println("Unknown instruction " + fields(1))
        }
      }
    }
    new Machine(labels, program)
  }
}

object Translator {
  def apply(file: String) = new Translator(file)
}
