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
    val iadd = new IAdd()
    assert(iadd.code === bytecode("iadd"))
  }

  val first = 3
  val second = 5
  val vm = new VirtualMachineMock(Vector(first, second))

  test("IAdd pushes the correct sum") {
    val iadd= new IAdd()

    assert(iadd.execute(vm).state.head === first + second)
  }
}
