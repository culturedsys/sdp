package abstractfactory

trait XMLParser[+T <: BusinessObject] {
  def parse(message: String): T
}
