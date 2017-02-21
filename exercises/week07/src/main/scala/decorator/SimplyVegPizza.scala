package decorator

class SimplyVegPizza extends Pizza {
  def getDesc: String = s"Simple veg base ($getPrice)"

  def getPrice: Double = 230
}
