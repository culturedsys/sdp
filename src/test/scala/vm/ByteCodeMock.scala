package vm

import bc.ByteCode

/**
  * A mock ByteCode which does nothing, but keeps track of how often its `execute` method is called.
  */
class ByteCodeMock extends ByteCode {
  /**
    * As a mock, rather than a real bytecode, this has no code value, so attempting to call this
    * should be an error
    */
  override lazy val code: Byte = throw new NotImplementedError

  /**
    * The number of times the `execute` method has been called on this object.
    */
  var executed: Int = 0

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    executed += 1
    vm
  }
}
