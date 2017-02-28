import scala.math.Ordering

case class Rational(numerator: Int, denominator: Int) {
    override def toString = s"${numerator}/${denominator}"
}

implicit val ordering = Ordering.fromLessThan[Rational] {
    (r1, r2) =>
        (r1.numerator * r2.denominator) < (r2.numerator * r1.denominator)
}

val viaFloat = Ordering.by[Rational, Double] {
    r => r.numerator.toDouble / r.denominator
}

List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted

List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted(viaFloat)


