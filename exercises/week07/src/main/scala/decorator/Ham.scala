package decorator

class Ham(val pizza: Pizza) extends Pizza {
  final val PRICE =   18.12

  def getDesc: String = pizza.getDesc + s", Ham ($PRICE)"

  def getPrice: Double = pizza.getPrice + PRICE
}
