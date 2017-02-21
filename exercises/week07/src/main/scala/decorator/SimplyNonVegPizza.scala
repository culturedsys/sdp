package decorator

class SimplyNonVegPizza extends Pizza {
  def getDesc: String = s"Simple non-veg base ($getPrice)"

  def getPrice: Double = 350
}
