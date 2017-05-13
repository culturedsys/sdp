package bc

import vm.VirtualMachine

/**
  * A mock of the stack-handling parts of the virtual machine, to test the effects of the bytecode implementations.
  * Intentionally does not implement the execution parts of the VirtualMachine trait.
  */
class VirtualMachineMock(val state: Vector[Int]) extends VirtualMachine {
  /** Intentionally unimplemented in this mock */
  override def execute(bc: Vector[ByteCode]): VirtualMachine = throw new NotImplementedError

  /** Intentionally unimplemented in this mock */
  override def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = throw new NotImplementedError

  /**
    * Pushes an integer value onto the virtual machine stack.
    *
    * @param value the integer to push
    * @return a new virtual machine with the integer `value` pushed
    */
  override def push(value: Int): VirtualMachine = new VirtualMachineMock(value +: state)

  /**
    * Pops an integer value off of the virtual machine stack.
    *
    * @return (i, vm), where i is the integer popped and vm is the
    *         new virtual machine
    */
  override def pop(): (Int, VirtualMachine) = (state.head, new VirtualMachineMock(state.tail))
}
