/**
  * A class containing a factory method for products
  */
trait Creator {
  def create(): Product = new ConcreteProduct
}

/**
  * A concrete implementation of the Creator, using its default method
  */
class ConcreteCreator extends Creator

/**
  * A specific implementation of the Product trait
  */
private class ConcreteProduct extends Product {
  /**
    * The name of the product
    */
  override def name: String = "Concrete"

  /**
    * The cost of the product, in pounds
    */
  override def cost: Double = 100.00
}
