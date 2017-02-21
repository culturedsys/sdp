package decorator

class Cheese(val pizza: Pizza) extends Pizza {
  final val PRICE = 20.72

  def getDesc: String = pizza.getDesc + s", Cheese ($PRICE)"

  def getPrice: Double = pizza.getPrice + PRICE
}
