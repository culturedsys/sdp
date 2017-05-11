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
}
