package interpreter

case class Product(left: Expression, right: Expression) extends Expression {
  def interpret = left.interpret * right.interpret
}
