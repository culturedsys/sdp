package bc

import org.scalatest.FunSpec

/**
  * Test the definition and operation of the various bytecodes
  */
class ByteCodeSpec extends FunSpec with ByteCodeValues {
  describe("iconst") {
    val iconst = new IConst(4)

    it("should have the correct code value") {
      assert(iconst.code === bytecode("iconst"))
    }

    it("should add the correct value to stack") {
      val vm = new VirtualMachineMock(Vector.empty)
      val value = 4
      val iconst = new IConst(value)

      assert(iconst.execute(vm).state.head === value)
    }
  }

  val first = 3
  val second = 5
  val vm = new VirtualMachineMock(Vector(first, second))

  describe("iadd") {
    val iadd = new IAdd

    it("should have the correct code value") {
      assert(iadd.code === bytecode("iadd"))
    }

    it("should push the correct sum") {
      assert(iadd.execute(vm).state.head === first + second)
    }

    it("should remove its operands from the stack") {
      assert(iadd.execute(vm).state.tail.isEmpty)
    }
  }

  describe("isub") {
    val isub = new ISub

    it("should have the correct code value") {
      assert(isub.code === bytecode("isub"))
    }

    it("should push the correct subtraction") {
      assert(isub.execute(vm).state.head === first - second)
    }

    it("should remove its operands from the stack") {
      assert(isub.execute(vm).state.tail.isEmpty)
    }
  }

  describe ("imul") {
    val imul = new IMul

    it("should have the correct code value") {
      assert(imul.code === bytecode("imul"))
    }

    it("should push the correct product") {
      assert(imul.execute(vm).state.head === first * second)
    }

    it("should remove its operands from the stack") {
      assert(imul.execute(vm).state.tail.isEmpty)
    }
  }
}
