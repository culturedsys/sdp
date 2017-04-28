package interpreter

case class Difference(left: Expression, right: Expression) extends Expression {
  def interpret = left.interpret - right.interpret
}
