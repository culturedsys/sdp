package bc
import vm.VirtualMachine

/** Implementation of the iconst bytecode.
  *
  * The iconst instruction pushes the integer value NUM on the virtual machine stack. VM.push(NUM)
  *
  * @param num the integer value to push on to the stack.
  */
class IConst(num: Int) extends ByteCode {
  /** A unique byte value representing the bytecode. */
  override val code: Byte = bytecode("iconst")

  /** Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = vm.push(num)
}

/** Implementation of the iadd bytecode.
  *
  * The iadd instruction pops the top two values from the virtual machine stack and pushes the result.
  * VM.push(VM.pop() + VM.pop())
  */
class IAdd extends ByteCode {
  /**
    * A unique byte value representing the bytecode.
    */
  override val code: Byte = bytecode("iadd")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (left, step1) = vm.pop()
    val (right, step2) = step1.pop()
    step2.push(left + right)
  }
}