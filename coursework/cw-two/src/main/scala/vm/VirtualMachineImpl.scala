package vm
import bc.ByteCode

import scala.annotation.tailrec
import scala.collection.immutable.Vector

/**
  * The implementation of the VirtualMachine trait.
  */
class VirtualMachineImpl(val state: Vector[Int]) extends VirtualMachine {

  /**
    * Executes a vector of bytecodes.
    *
    * Note, that this is an "immutable" object. That is, it
    * will return a new virtual machine after executing each
    * of the bytecode objects in the vector.
    *
    * @param bc a vector of bytecodes
    * @return a new virtual machine
    */
  override def execute(bc: Vector[ByteCode]): VirtualMachine = {
    if (bc.nonEmpty) {
      val (restByteCodes, resultVm) = executeOne(bc)
      resultVm.execute(restByteCodes)
    }
    else
      // The specification states that if a sequence of bytecodes runs successfully, the
      // resulting VM should have an empty state.
      new VirtualMachineImpl(Vector.empty)
  }

  /**
    * Executes the next bytecode in the vector of bytecodes.
    *
    * This method only executes a single byte code in the vector of bytecodes.
    * It returns a tuple of the new vector of bytecodes (with the executed
    * bytecode removed) and the new virtual machine.
    *
    * You may assume that `bc` contains at least 1 bytecode.
    *
    * @param bc the vector of bytecodes
    * @return a tuple of a new vector of bytecodes and virtual machine
    */
  override def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = bc match {
    case first +: rest => (rest, first.execute(this))
    // The specification does not mandate any behaviour for an empty vector, but it seems better
    // to explicitly handle the case.
    case _ => throw new IllegalArgumentException
  }

  /**
    * Pushes an integer value onto the virtual machine stack.
    *
    * @param value the integer to push
    * @return a new virtual machine with the integer `value` pushed
    */
  override def push(value: Int): VirtualMachine = new VirtualMachineImpl(value +: state)

  /**
    * Pops an integer value off of the virtual machine stack.
    *
    * @return (i, vm), where i is the integer popped and vm is the
    *         new virtual machine
    */
  override def pop(): (Int, VirtualMachine) = state match {
    case head +: rest => (head, new VirtualMachineImpl(rest))
    case _ => throw new MachineUnderflowException("Stack underflow in virtual machine")
  }

  // Note that this doesn't include an explicit override of the state method, as Scala can
  // auto-generate accessors for `val` constructor parameters.
}
