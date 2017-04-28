// Q3

// Insertion sort, where g is the opposite of the ordering function
def f(xs: List[Int], g: (Int, Int) => Boolean) = {
  // Insert x in xs such that g(x, y) is true for every y before x
  def h(x: Int, xs: List[Int]): List[Int] = {
    xs match {
      case List() => List(x)
      case y :: ys => if (!g(x, y)) x :: xs else y :: h(x, ys)
    }
  }

  (xs :\ List[Int]())(h)
}

f(List(3, 6, 1, 3, 8, 7, 9, 1), (_ > _))

def flatten[A](as: List[List[A]]): List[Any] =
  as match {
    case List() => List()
    case x :: xs => x ::: flatten(xs)
  }

// Q8

def allExceptOption(needle: String, haystack: List[String]): Option[List[String]] =
  haystack match {
    case Nil => None
    case x :: xs =>
      if (x == needle)
        Some(xs)
      else
        allExceptOption(needle, xs) match {
          case None => None
          case Some(rest) => Some(x :: rest)
        }
  }

allExceptOption("item", List("fred", "knife", "butter"))
allExceptOption("knife", List("fred", "knife", "butter"))

def getSubstitutions1(needle: String, haystacks: List[List[String]]): List[String] =
  haystacks match {
    case Nil => Nil
    case x :: xs =>
      val f = allExceptOption(needle, x) match {
        case None => Nil
        case Some(x) => x
      }
      f ::: getSubstitutions1(needle, xs)
  }

val l = List(
  List("Fred", "Fredrick"),
  List("Elizabeth", "Betty"),
  List("Freddie", "Fred", "F")
)

getSubstitutions1("Fred", l)

def getSubstitutions2(needle: String, haystacks: List[List[String]]) = {
  def helper(acc: List[String], strings: List[List[String]]): List[String] =
    strings match {
      case Nil => acc
      case x :: xs =>
        allExceptOption(needle, x) match {
          case None => helper(acc, xs)
          case Some(l) => helper(acc ::: l, xs)
        }
    }
  helper(Nil, haystacks)
}

getSubstitutions2("Fred", l)


def similarNames(name: (String, String, String), subsLists: List[List[String]]) = {
  val (first, middle, last) = name

  def substitute(subs: List[String]): List[(String, String, String)] =
    subs match {
      case Nil => Nil
      case x :: xs => (x, middle, last) :: substitute(xs)
    }

  substitute(getSubstitutions2(first, subsLists))
}

similarNames(("Fred", "W", "Smith"), l)


// Q9
sealed trait Expr
final case class Number(num: Double) extends Expr
final case class Var(name: String) extends Expr
final case class Plus(left: Expr, right: Expr) extends Expr
final case class Fun(param: String, body: Expr) extends Expr
final case class App(fun: Expr, arg: Expr) extends Expr
final case class Let(name: String, expr: Expr, body: Expr) extends Expr

trait Value
case class NumValue(num: Double) extends Value
case class ClosureValue(param: String,
                        body: Expr,
                        env: Evaluation.Env) extends Value

object Operations {
  def reduce(expr: Expr) =
    expr match {
      case Plus(Number(i), Number(j)) => Number(i + j)
      case Plus(Number(0), e) => e
      case App(Fun(param, body), e) => Let(param, e, body)
      case Let(x, e, Var(y)) if x == y => e
      case _ => expr
    }

  def simplify(expr: Expr): Expr =  expr match{
    case Number(d) => Number(d)
    case Var(name) => Var(name)
    case Plus(left, right) => reduce(Plus(simplify(left), simplify(right)))
    case Let(name, expr, body) => Let(name, simplify(expr), simplify(body))
    case Fun(param, body) => Fun(param, simplify(body))
    case App(fun, arg) => reduce(App(simplify(fun), simplify(arg)))
  }
}

object Evaluation {
  type Env = Map[String, Value]

  def makeEnv(): Env = Map()

  val num: Value => Double = {
    case NumValue(num) => num
  }

  def plus(x: Value, y: Value) = NumValue(num(x) + num(y))
  def eval(expr: Expr): Value = eval(expr, makeEnv())
  def eval(expr: Expr, env: Env): Value = {
    expr match {
      case Number(d) => NumValue(d)
      case Var(name) => env(name)
      case Plus(left, right) => plus(eval(left, env), eval(right, env))
      case Let(name, expr, body) =>
        eval(body, env + (name -> eval(expr, env)))
      case Fun(param, body) => ClosureValue(param, body, env)
      case App(fun, arg) =>
        val ClosureValue(param, body, env2) = eval(fun, env)
        val v = eval(arg, env)
        eval(body, env2 + (param -> v))
    }
  }
}

Evaluation.eval {
  Let("x", Number(1),
    Let("f", Fun("y", Plus(Var("x"), Var("y"))),
      Let("x", Number(2),
        App(Var("f"), Number(3)))))
}

Evaluation.eval {
  Let("y", Number(3),
    Let("f", Fun("x", Plus(Var("x"), Var("y"))), Var("f")))
}

Operations.simplify {
  App(Fun("y", Plus(Number(1), Var("y"))), Plus(Number(2), Number(3)))
}