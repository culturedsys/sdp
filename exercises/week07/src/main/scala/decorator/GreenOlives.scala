package decorator

class GreenOlives(val pizza: Pizza) extends Pizza {
  final val PRICE = 5.47

  def getDesc: String = pizza.getDesc + s", Green Olives ($PRICE)"

  def getPrice: Double = pizza.getPrice + PRICE
}
