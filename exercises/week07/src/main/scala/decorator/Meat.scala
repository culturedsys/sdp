package decorator

class Meat(val pizza: Pizza) extends Pizza {
  final val PRICE = 14.25

  def getDesc: String = pizza.getDesc + s", Meat ($PRICE)"

  def getPrice: Double = pizza.getPrice + PRICE
}
