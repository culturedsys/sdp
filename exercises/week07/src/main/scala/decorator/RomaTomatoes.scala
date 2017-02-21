package decorator

class RomaTomatoes(val pizza: Pizza) extends Pizza {
  final val PRICE = 5.20

  def getDesc: String = pizza.getDesc + s", Roma Tomatoes ($PRICE)"
  def getPrice: Double = pizza.getPrice + PRICE
}
