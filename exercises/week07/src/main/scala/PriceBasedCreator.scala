/**
  * A Creator that can produce different sorts of Product, depending on an argument supplied to its constructor
  */
class PriceBasedCreator(maxPrice: Int) extends Creator {
  override def create() =
    if (maxPrice < 10)
      new FreeProduct
    else if (maxPrice < 20)
      new CheapProduct
    else
      new ExpensiveProduct
}

private class FreeProduct extends Product {
  override def name: String = "Free product"

  override def cost: Double = 0
}

private class CheapProduct extends Product {
  override def name: String = "Cheap product"

  override def cost: Double = 10
}

private class ExpensiveProduct extends Product {
  override def name: String = "Expensive product"

  override def cost: Double = 20
}

