package vm

import bc.{ByteCode, InvalidBytecodeException}
import factory.VirtualMachineFactory
import vendor.{CommandParser, Instruction, ProgramParser}
import vm.VirtualMachineParserImpl.bytecode

class VirtualMachineParserImp extends VirtualMachineParser{

  val vendorParser: ProgramParser = new CommandParser()
  /**
    * Returns a vector of [[bc.ByteCode]].
    *
    * This method parses a file into a vector of bytecode objects.
    * Note, this method should throw a [[bc.InvalidBytecodeException]]
    * if it fails to parse a program file correctly.
    *
    * @param file the file containing a program
    * @return a vector of bytecodes
    */
  def parse(file: String): Vector[ByteCode]={
    val result : Vector[Instruction]=vendorParser.parse(file)
    helper(result)
  }

  /**
    * Returns a vector of [[bc.ByteCode]].
    *
    * This method parses a string into a vector of bytecode objects.
    * Note, this method should throw a [[bc.InvalidBytecodeException]]
    * if it fails to parse a program string correctly.
    *
    * @param str a string containing a program
    * @return a vector of bytecodes
    */
  def parseString(str: String): Vector[ByteCode]={
    val result: Vector[Instruction] = vendorParser.parseString(str)
    helper(result)
  }

  private def helper(instructions: Vector[Instruction])= {
    VirtualMachineFactory.byteCodeParser.parse(instructions.flatMap(encodeInstruction))
  }


  /**
    * Convert vendor instruction into a vector of bytes. Checks the validity of the instruction in two ways: checks that
    * the name of the instruction is the name of an operation with a known code, and checks that the values of the
    * argument are in range for Byte (the example given in the question suggests that only one byte is allowed as an
    * argument for iconst). It does not check that the Instruction has the correct number of arguments; that will be
    * checked by the ByteCodeParser.
    *
    * @param instruction the instruction in the vendor format
    * @return a Vector of Bytes encoding this instruction
    * @throws InvalidBytecodeException if it cannot encode this instruction as a Byte code
    */
  def encodeInstruction(instruction: Instruction): Vector[Byte] = {
    val opcode = bytecode.getOrElse(instruction.name, throw new InvalidBytecodeException(s"No bytecode for ${instruction.name}"))
    val args = instruction.args.map { i =>
      if (i < Byte.MinValue || i > Byte.MaxValue)
        throw new InvalidBytecodeException(s"Integer value $i out of range for Byte")
      else
        i.toByte
    }
    opcode +: args
  }

}
