package vm

import org.scalatest.FunSpec

/**
  * Tests for the VirtualMachineImpl (this class only includes tests for VirtualMachineSpec, as
  * distinct from the tests in PublicVirtualMachineSuite, which test the combination of the
  * VirtualMachine and the VirtualMachineParser).
  */
class VirtualMachineImplSpec extends FunSpec {
  val emptyVm = new VirtualMachineImpl(Vector.empty)
  val nonEmptyVm = new VirtualMachineImpl(Vector(10, 20, 30))

  describe("push") {
    it("should push a value onto an empty stack") {
      assert(emptyVm.push(11).state === Vector(11))
    }

    it("should push a value onto the front of a non-empty stack") {
      assert(nonEmptyVm.push(40).state === Vector(40, 10, 20, 30))
    }
  }

  describe("pop") {
    it("should pop a value from a non-empty stack") {
      assert(nonEmptyVm.pop()._1 === 10)
    }

    it("should remove the popped value from the stack") {
      assert(nonEmptyVm.pop()._2.state === Vector(20, 30))
    }

    it("should throw when attempting to pop from a non-empty stack") {
      intercept[MachineUnderflowException] {
        emptyVm.pop()
      }
    }
  }

  describe("executeOne") {
    it("should call execute on one bytecode") {
      val bc = new ByteCodeMock
      emptyVm.executeOne(Vector(bc))
      assert(bc.executed === 1)
    }

    it("should call execute on the first bytecode") {
      val bc1 = new ByteCodeMock
      val bc2 = new ByteCodeMock
      emptyVm.executeOne(Vector(bc1, bc2))
      assert(bc1.executed === 1)
      assert(bc2.executed === 0)
    }

    it("should return the remaining bytecodes") {
      val bc1 = new ByteCodeMock
      val bc2 = new ByteCodeMock
      val bc3 = new ByteCodeMock

      val (rest, _) = emptyVm.executeOne(Vector(bc1, bc2, bc3))
      assert(rest === Vector(bc2, bc3))
    }
  }

  describe("execute") {
    it("should return an empty vm on an empty vector of bytecode") {
      assert(nonEmptyVm.execute(Vector.empty).state === Vector.empty)
    }

    it("should return an empty vm on a vector with multiple bytecodes") {
      val bc1 = new ByteCodeMock
      val bc2 = new ByteCodeMock
      val bc3 = new ByteCodeMock

      assert(emptyVm.execute(Vector(bc1, bc2, bc3)).state === Vector.empty)
    }

    it("should execute all the bytecodes in a vector") {
      val bc1 = new ByteCodeMock
      val bc2 = new ByteCodeMock
      val bc3 = new ByteCodeMock

      emptyVm.execute(Vector(bc1, bc2, bc3))
      assert(bc1.executed === 1)
      assert(bc2.executed === 1)
      assert(bc3.executed === 1)
    }
  }
}
