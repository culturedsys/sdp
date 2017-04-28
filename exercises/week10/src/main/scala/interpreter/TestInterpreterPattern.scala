package interpreter

object TestInterpreterPattern extends App {
  val tokenString: String = "7 3 - 2 1 + *"
  val expressionStack: Option[List[Expression]] = tokenString.split(" ").foldLeft(Some(List[Expression]()).asInstanceOf[Option[List[Expression]]]) {
    (expressions: Option[List[Expression]], token) =>
    val parsed = token match {
      case "+" => Left(Add)
      case "*" => Left(Product)
      case "-" => Left(Difference)
      case _ => Right(Literal(token.toInt))
    }

    parsed match {
      case Right(literal) => expressions.map(e => literal :: e)
      case Left(op) =>
        expressions.flatMap {
          case right :: left :: rest => Some(op(left, right) :: rest)
          case _ => None
        }
    }
  }

  expressionStack match {
    case Some(expressions) => println(s"($tokenString):  ${expressions.head.interpret}")
    case _ => println("Parse error")
  }
}
