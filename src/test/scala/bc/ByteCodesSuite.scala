package bc

import org.scalatest.FunSuite

/**
  * Test the definition and operation of the various bytecodes
  */
class ByteCodesSuite extends FunSuite with ByteCodeValues {
  test("IConst has the correct code value") {
    val iconst = new IConst(4)
    assert(iconst.code === bytecode("iconst"))
  }

  test("IConst adds correct value to stack") {
    val vm = new VirtualMachineMock(Vector.empty)
    val value = 4
    val iconst = new IConst(value)

    assert(iconst.execute(vm).state.head === value)
  }

  test("IAdd has the correct code value") {
    val iadd = new IAdd
    assert(iadd.code === bytecode("iadd"))
  }

  val first = 3
  val second = 5
  val vm = new VirtualMachineMock(Vector(first, second))

  val iadd= new IAdd()

  test("IAdd pushes the correct sum") {
    assert(iadd.execute(vm).state.head === first + second)
  }

  test("IAdd removes its operands from the stack") {
    assert(iadd.execute(vm).state.tail.isEmpty)
  }

  val isub = new ISub

  test("ISub has the correct code value") {
    assert(isub.code === bytecode("isub"))
  }

  test("ISub pushes the correct subtraction") {
    assert(isub.execute(vm).state.head === first - second)
  }

  test("ISub removes its operands from the stack") {
    assert(isub.execute(vm).state.tail.isEmpty)
  }
}
