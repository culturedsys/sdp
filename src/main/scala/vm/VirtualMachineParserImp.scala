package vm

import bc.{IAdd, _}
import vendor.{CommandParser, Instruction, ProgramParser}

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
    val result:Vector[Instruction]=vendorParser.parseString(str)
    helper(result)
  }

  private def helper(result:Vector[Instruction])={
    result.map(x=>{
      x.name match{
        case "print" => ByteCodeFactoryImpl.make(new Print().code,x.args:_*)
        case "iadd"   => ByteCodeFactoryImpl.make(new IAdd().code,x.args:_*)
        case "idup" => ByteCodeFactoryImpl.make(new IDup().code,x.args:_*)
        case "iswap"   => ByteCodeFactoryImpl.make(new ISwap().code,x.args:_*)
        case "iinc" => ByteCodeFactoryImpl.make(new IInc().code,x.args:_*)
        case "ineg"   => ByteCodeFactoryImpl.make(new INeg().code,x.args:_*)
        case "irem" => ByteCodeFactoryImpl.make(new IRem().code,x.args:_*)
        case "idiv"   => ByteCodeFactoryImpl.make(new IDiv().code,x.args:_*)
        case "imul" => ByteCodeFactoryImpl.make(new IMul().code,x.args:_*)
        case "isub"   => ByteCodeFactoryImpl.make(new ISub().code,x.args:_*)
      }
    })
  }

}
