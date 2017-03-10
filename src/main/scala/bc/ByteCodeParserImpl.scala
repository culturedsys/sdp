package bc

/**
  * An implementation of the ByteCodeParser trait.
  *
  * @param byteCodeFactory a ByteCodeFactory used to construct the ByteCode objects on the basis of the bytes supplied
  */
class ByteCodeParserImpl(byteCodeFactory: ByteCodeFactory) extends ByteCodeParser {

  /** A set of all the byte code values that have an argument (currently just iconst) */
  private val codesWithArguments = Set(bytecode("iconst"))

  /**
    * Parses a vector of `Byte` into a vector of `ByteCode`.
    *
    * You should use [[ByteCodeValues.bytecode]] to help translate
    * the individual `Byte`s into a correponding [[ByteCode]].
    *
    * @param bc a vector of bytes representing bytecodes
    * @return a vector of `ByteCode` objects
    */
  override def parse(bc: Vector[Byte]): Vector[ByteCode] = {
    def helper(acc: Vector[ByteCode], codes: Vector[Byte]): Vector[ByteCode] = codes match {
      case Vector() => acc
      case code +: rest =>
        if (codesWithArguments.contains(code))
          if (rest.isEmpty)
            throw new InvalidBytecodeException("Missing argument")
          else
            helper(acc :+ byteCodeFactory.make(code, rest.head), rest.tail)
        else
          helper(acc :+ byteCodeFactory.make(code), rest)
    }

    helper(Vector.empty, bc)
  }
}
