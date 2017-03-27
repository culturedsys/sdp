package sml

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
class Translator(fileName: String) {
  /**
    * Translate an instruction name, as used in the input, to a class name. Note that this always produces
    * fully-qualified names in the "sml" package.
    *
    * The format of the
    *
    * @param instruction  the instruction name
    */
  def classNameForInstruction(instruction: String): String =
    "sml." + instruction(0).toUpper + instruction.substring(1).toLowerCase + "Instruction"

  /**
    * Create an instance of the correct instruction type from the supplied fields.
    *
    * Uses reflection to find the class to use. The name of the class is derived from the opcode name using the
    * classNameForInstruction method. The class must have a constructor which takes the same number of parameters as
    * their are elements of the 'fields' array. The first two arguments to the constructor must be strings,
    * representing the label and the opcode name. The remaining arguments to the constructor must be either of type
    * string or type int (or Integer; the java reflection code automatically boxes/unboxes ints and Integers
    * when calling constructors). Any Strings in the fields array corresponding to Integer parameters are converted to
    * Integers before being passed to the constructor. If there is more than one constructor with the correct
    * number and types of arguments, whichever Class.getConstructors returns first is used.
    *
    * @param fields an array of strings representing the elements of the instruction line in the source
    * @return Some instance of the appropriate instruction type, or None if the class doesn't exist, or doesn't have
    *         an appropriate constructor
    */
  def createInstruction(fields: Array[String]): Option[Instruction] = {
    try {
      val cls = Class.forName(classNameForInstruction(fields(1)))

      val possibleConstructors = cls.getConstructors.filter { c =>
        val paramTypes = c.getParameterTypes
        c.getParameterCount == fields.length &&
          paramTypes(0) == classOf[String] &&
          paramTypes(1) == classOf[String]
      }

      possibleConstructors.headOption.map { constructor =>
        val paramsWithTypes = fields.zip(constructor.getParameterTypes)
        val params = paramsWithTypes.map {
          case (param, paramType) =>
            if (paramType == classOf[Integer] || paramType == classOf[Int]) {
              Integer.valueOf(param)
            } else {
              param
            }
        }
        constructor.newInstance(params:_*).asInstanceOf[Instruction]
      }
    } catch {
      case _ : ClassNotFoundException => None
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

      if (fields.nonEmpty) {
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
  def apply(file: String): Translator = new Translator(file)
}
