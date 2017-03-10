package bc

import org.scalatest.FunSpec

/**
  * Test the definition and operation of the various bytecodes
  */
class ByteCodesSpec extends FunSpec with ByteCodeValues {
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

  describe("Binary operations") {
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

    describe("imul") {
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

    describe("idiv") {
      val idiv = new IDiv

      it("should have the correct code value") {
        assert(idiv.code === bytecode("idiv"))
      }

      it("should push the correct quotient") {
        assert(idiv.execute(vm).state.head === first / second)
      }

      it("should remove its operands from the stack") {
        assert(idiv.execute(vm).state.tail.isEmpty)
      }

      it("should throw ArithmeticException on divide by zero") {
        intercept[ArithmeticException] {
          idiv.execute(new VirtualMachineMock(Vector(1, 0)))
        }
      }
    }

    describe("irem") {
      val irem = new IRem

      it("should have the correct code value") {
        assert(irem.code === bytecode("irem"))
      }

      it("should push the correct remainder") {
        assert(irem.execute(vm).state.head === first % second)
      }

      it("should remove its operands from the stack") {
        assert(irem.execute(vm).state.tail.isEmpty)
      }

      it("should throw ArithmeticException on divide by zero") {
        intercept[ArithmeticException] {
          irem.execute(new VirtualMachineMock(Vector(1, 0)))
        }
      }
    }

    describe("iswap") {
      val iswap = new ISwap

      it("should have the correct code") {
        assert(iswap.code === bytecode("iswap"))
      }

      it("should swap the top two stack members") {
        val result = iswap.execute(vm)

        assert(result.state.head === second)
        assert(result.state.tail.head === first)
      }

      it("should not change the length of the stack") {
        assert(iswap.execute(vm).state.size === vm.state.size)
      }
    }
  }

  describe("Unary operations") {

    val value = 10
    val vm = new VirtualMachineMock(Vector(value))

    describe("ineg") {
      val ineg = new INeg

      it("should have the correct code value") {
        assert(ineg.code === bytecode("ineg"))
      }

      it("should push the correct negative value") {
        assert(ineg.execute(vm).state.head === -value)
      }

      it("should should not alter the stack length") {
        assert(ineg.execute(vm).state.size === vm.state.size)
      }
    }

    describe("iinc") {
      val iinc = new IInc

      it("should have the correct code value") {
        assert(iinc.code == bytecode("iinc"))
      }

      it("should push the correct incremented value") {
        assert(iinc.execute(vm).state.head === value + 1)
      }

      it("should not alter the stack length") {
        assert(iinc.execute(vm).state.size === vm.state.size)
      }
    }

    describe("idec") {
      val idec = new IDec

      it("should have the correct code value") {
        assert(idec.code === bytecode("idec"))
      }

      it("should push the correct decremented value") {
        assert(idec.execute(vm).state.head === value - 1)
      }

      it("should not alter the stack length") {
        assert(idec.execute(vm).state.size === vm.state.size)
      }
    }

    describe("idup") {
      val idup = new IDup

      it("should have the correct code value") {
        assert(idup.code === bytecode("idup"))
      }

      it("should duplicate the top value") {
        val result = idup.execute(vm)
        assert(result.state.head === value)
        assert(result.state.tail.head === value)
      }

      it("should increase the stack length by one") {
        assert(idup.execute(vm).state.size === vm.state.size + 1)
      }
    }
  }
}
