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

/** Implementation of the ineg bytecode
  *
  * The ineg instruction pops the the top value from the virtual machine stack, negates it, and pushes the result.
  * VM.push(-VM.pop())
  */
class INeg extends ByteCode {
  /** A unique byte value representing the bytecode. */
  override val code: Byte = bytecode("ineg")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (operand, result) = vm.pop()
    result.push(-operand)
  }
}

/** Implementation of the iinc bytecode.
  *
  * The iinc instruction pops the the top value from the virtual machine stack, increments it, and pushes the result.
  * VM.push(VM.pop()+1)
  */
class IInc extends ByteCode {
  /** A unique byte value representing the bytecode. */
  override val code: Byte = bytecode("iinc")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (operand, result) = vm.pop()
    result.push(operand + 1)
  }
}

/** Implementation of the idec bytecode
  *
  * The idec instruction pops the the top value from the virtual machine stack, decrements it, and pushes the result.
  * VM.push(VM.pop()-1)
  */
class IDec extends ByteCode {
  /**
    * A unique byte value representing the bytecode. */
  override val code: Byte = bytecode("idec")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (operand, result) = vm.pop()
    result.push(operand - 1)
  }
}


/** Implementation of the iswap bytecode
  *
  * The iswap instruction pops the top two values from the virtual machine stack and pushes them in the opposite
  * order, effectively swapping the top two values of the stack.
  * x = VM.pop();
  * y = VM.pop();
  * VM.push(x);
  * VM.push(y)
  */
class ISwap extends ByteCode {
  /**
    * A unique byte value representing the bytecode */
  override val code: Byte = bytecode("iswap")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (top, step1) = vm.pop()
    val (next, step2) = step1.pop()
    step2.push(top).push(next)
  }
}

/** Implementation of the idup bytecode
  *
  * The idup instruction pops the top value from the stack and pushes it twice onto the stack (duplicates the top
  * value).
  *
  * x = VM.pop();
  * VM.push(x);
  * VM.push(x).
  */
class IDup extends ByteCode {
  /** A unique byte value representing the bytecode. */
  override val code: Byte = bytecode("idup")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (top, result) = vm.pop()
    result.push(top).push(top)
  }
}

/** Implementation of the print bytecode
  *
  * The print instruction pops the top value from the stack and prints the value to the console.
  *
  * The specification does not say that a newline or other separator should be printed after the value, but this seems
  * useful (otherwise there would be no way of telling apart the output of separate print instructions), so this
  * implementation adds a newline after printing the value.
  */
class Print extends ByteCode {
  /** A unique byte value representing the bytecode.*/
  override val code: Byte = bytecode("print")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (top, result) = vm.pop()
    println(top)
    result
  }
}