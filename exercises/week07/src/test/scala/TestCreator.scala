import org.scalatest.FunSpec

/**
  * Test that a ConcreteCreator can create a Product
  */
class TestCreator extends FunSpec {
  describe("ConcreteCreator") {
    it("can create a Product") {
      val creator = new ConcreteCreator
      val product : Product = creator.create()
      assert(product != null)
    }
  }
}
