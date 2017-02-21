import org.scalatest.FunSpec

/**
  * Test the PriceBasedCreator
  */
class TestPriceBasedCreator extends FunSpec {
  describe("PriceBasedCreator") {
    it("can create a free product") {
      val creator = new PriceBasedCreator(0)
      val product = creator.create()
      assert(product.cost === 0)
    }

    it("can create a cheap product") {
      val creator = new PriceBasedCreator(10)
      val product = creator.create()
      assert(product.cost <= 10)
    }

    it("can create an expensive product") {
      val creator = new PriceBasedCreator(100)
      val product = creator.create()

      assert(product.cost > 10)
      assert(product.cost <= 100)
    }
  }
}
