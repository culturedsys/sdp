import org.scalatest.FunSpec

/**
  * Tests for the various (simple and more complex) singletons
  */
class TestSingletons extends FunSpec {
  describe("SimpleSingleton") {
    it("can create an instance") {
      val instance = SimpleSingleton.getInstance
      assert(instance.getPi === Math.PI)
    }

    it("will only create one instance") {
      val instance1 = SimpleSingleton.getInstance
      val instance2 = SimpleSingleton.getInstance
      assert(System.identityHashCode(instance1) === System.identityHashCode(instance2))
    }
  }

  describe("ExpensiveSingleton") {
    it("can create an instance") {
      val instance = ExpensiveSingleton.getInstance

      assert(instance.getFibonacci(9) === Some(55))
    }

    it("will only create one instance") {
      val instance1 = ExpensiveSingleton.getInstance
      val instance2 = ExpensiveSingleton.getInstance
      assert(System.identityHashCode(instance1) === System.identityHashCode(instance2))
    }
  }

  describe("ParameterizedSingleton") {
    it("throws if a name is not specified") {
      assertThrows[IllegalStateException] {
        ParameterizedSingleton.getInstance
      }
    }

    it("can create an instance") {
      val name = "Herr Doktor Professor Singleton"
      ParameterizedSingleton.setName(name)
      val instance = ParameterizedSingleton.getInstance
      assert(instance.getName === name)
    }

    it("throws if name specified after instance is created") {
      assertThrows[IllegalStateException] {
        ParameterizedSingleton.setName("Some name")
      }
    }
  }
}
