package  interpreter

case class Add(left: Expression, right: Expression) extends Expression {
  def interpret = left.interpret + right.interpret
}