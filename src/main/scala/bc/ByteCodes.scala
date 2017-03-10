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

/** Shared code for arithmetic bytecodes
  *
  * @param op the binary operation to execute on the top two values on the stack.
  */
abstract class ArithmeticByteCode(op: (Int, Int) => Int) extends ByteCode {
  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (left, step1) = vm.pop()
    val (right, step2) = step1.pop()
    step2.push(op(left, right))
  }
}


/** Implementation of the iadd bytecode.
  *
  * The iadd instruction pops the top two values from the virtual machine stack and pushes the result.
  * VM.push(VM.pop() + VM.pop())
  */
class IAdd extends ArithmeticByteCode(_ + _) {
  /**
    * A unique byte value representing the bytecode.
    */
  override val code: Byte = bytecode("iadd")
}

/** Implementation of the isub bytecode
  *
  * The isub instruction pops the top two values from the virtual machine stack and pushes the result.
  * VM.push(VM.pop() - VM.pop())
  */
class ISub extends ArithmeticByteCode(_ - _) {
  /**
    * A unique byte value representing the bytecode.
    */
  override val code: Byte = bytecode("isub")
}

/** Implementation of the imul bytecode
  *
  * The imul instruction pops the top two values from the virtual machine stack and pushes the result.
  * VM.push(VM.pop() * VM.pop())
  */
class IMul extends ArithmeticByteCode(_ * _) {
  /**
    * A unique byte value representing the bytecode.
    */
  override val code: Byte = bytecode("imul")
}

/** Implementation of the idiv bytecode.
  *
  * The idiv instruction pops the top two values from the virtual machine stack and pushes the result.
  * VM.push(VM.pop() / VM.pop())
  *
  * The specification does not say what to do in the case of divide by zero. This implementation simply throws the
  * ArithmeticException in that case.
  */
class IDiv extends ArithmeticByteCode(_ / _) {
  /**
    * A unique byte value representing the bytecode.
    */
  override val code: Byte = bytecode("idiv")
}


/** Implementation of the irem bytecode.
  *
  * The irem instruction pops the top two values from the virtual machine stack and pushes the result.
  * VM.push(VM.pop() % VM.pop())
  *
  * The specification does not say what to do in the case of divide by zero. This implementation simply throws the
  * ArithmeticException in that case.
  */
class IRem extends ArithmeticByteCode(_ % _) {
  /**
    * A unique byte value representing the bytecode.
    */
  override val code: Byte = bytecode("irem")
}