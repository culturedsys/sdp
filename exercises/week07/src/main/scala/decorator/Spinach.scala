package decorator

class Spinach(val pizza: Pizza) extends Pizza {
  final val PRICE = 7.92

  def getDesc: String = pizza.getDesc + s", Spinach ($PRICE)"

  def getPrice: Double = pizza.getPrice + PRICE
}
