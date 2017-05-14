package bc

/**
  * An implementation of the ByteCodeFactory trait. Implemented as an object, rather than a class, because there is
  * no reason to have more than one instance of this factory - every instance would behave the same. Thus it is
  * implemented as an object, the simplest way in Scala to implement the singleton pattern.
  */
object ByteCodeFactoryImpl extends ByteCodeFactory with ByteCodeValues {

  private lazy val namesForBytes = bytecode.map(_.swap)

  /**
    * Returns a [[ByteCode]].
    *
    * This method creates a new [[ByteCode]] object given the `byte`
    * that corresponds to the bytecode (see [[ByteCodeValues]]. If
    * the bytecode requires arguments then an optional integer
    * argument is provided.
    *
    * This method should throw an [[InvalidBytecodeException]] if the
    * given bytecode value is unknown.
    *
    * @param byte the byte code of a bytecode
    * @param args an optional integer argument (depends on bytecode)
    * @return a new bytecode object
    */
  override def make(byte: Byte, args: Int*): ByteCode = namesForBytes.get(byte) match {
    case Some("iconst") =>
      if (args.isEmpty)
        throw new InvalidBytecodeException("iconst must have an argument")
      new IConst(args(0))
    case Some("iadd") => new IAdd
    case Some("isub") => new ISub
    case Some("imul") => new IMul
    case Some("idiv") => new IDiv
    case Some("irem") => new IRem
    case Some("ineg") => new INeg
    case Some("iinc") => new IInc
    case Some("idec") => new IDec
    case Some("iswap") => new ISwap
    case Some("idup") => new IDup
    case Some("print") => new Print
    case _ => throw new InvalidBytecodeException(s"Unknown bytecode $byte")
  }
}
