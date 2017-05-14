package factory

import bc.{ByteCodeFactory, ByteCodeFactoryImpl, ByteCodeParser, ByteCodeParserImpl}
import vendor.{CommandParser, ProgramParser}
import vm._

/**
  * The `VirtualMachineFactory` follows the *factory pattern*. It provides
  * methods for each of the important parts in this assignment. You must
  * implement each method such that it returns an object of the correct type.
  */
object VirtualMachineFactory {
  /**
    * Get a default implementation of ByteCodeFactory.
    */
  val byteCodeFactory: ByteCodeFactory = ByteCodeFactoryImpl

  /**
    * Get a default implementation of ProgramParser
    */
    // As CommandParser is immutable, we only need one instance, which we can lazily create.
  lazy val vendorParser: ProgramParser = new CommandParser

  /**
    * Get a default implementation of ByteCodeParser.
    */
  // As ByteCodeParserImpl is immutable, we only need one default instance. The instance can be a
  // lazy val to delay creation until it is required.
  lazy val byteCodeParser: ByteCodeParser = new ByteCodeParserImpl(byteCodeFactory)

  /**
    * Get a default implementation of VirtualMachineParser
    */
    // As VirtualMachineParserImp is immutable, we only need one instance, which we can lazily
    // create.
  lazy val virtualMachineParser: VirtualMachineParser = new VirtualMachineParserImp

  /**
    * Get a default implementation of VirtualMachine.
    */
  // as VirtualMachineImpl is immutable, we only need one default instance. The instance can be a
  // lazy val to delay creation until it is required.
  lazy val virtualMachine: VirtualMachine = new VirtualMachineImpl(Vector.empty)
}
