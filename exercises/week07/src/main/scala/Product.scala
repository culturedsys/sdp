/**
  * Something that is produced by a factory.
  */
trait Product {
  /**
    * The name of the product
    */
  def name: String

  /**
    * The cost of the product, in pounds
    */
  def cost: Double
}
