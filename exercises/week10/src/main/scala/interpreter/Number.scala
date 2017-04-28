package interpreter

case class Literal(value: Int) extends Expression {
  override def interpret: Int = value
}
