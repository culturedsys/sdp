package builder

/**
  * I've changed this to parameterize the "build" method. This is because there's no point in using the builder
  * pattern if the director always calls exactly the same methods on the builder instances. The builder pattern is
  * useful if construction of the instance requires building the object step-by-step, i.e. if there could be a
  * varying number of steps in different cases.
  */
case class CarDirector(carBuilder: CarBuilder) {

  def build(features: Seq[Feature]) = {
      for (feature <- features)
          feature match {
              case body: Body => carBuilder.buildBodyStyle(body)
              case power: Power => carBuilder.buildPower(power)
              case engine: Engine => carBuilder.buildEngine(engine)
              case breaks: Breaks => carBuilder.buildBreaks(breaks)
              case seat: Seat => carBuilder.buildSeats(seat)
              case window: Window => carBuilder.buildWindows(window)
              case fuel: Fuel => carBuilder.buildFuelType(fuel)
          }
      carBuilder.getCar
  }
}

/**
  * Different features that can be added to a car. They are used to describe a Car to the director and the builder
  * classes. The specific way these are dealt with depends on the CarBuilder instance being used.
  */
sealed trait Feature {
    def description: String
}

final case class Body(length: Int, width: Int) extends Feature {
    override def description = s"Dimensions: ${length}x${width}"
}

final case class Power(bhp: Int, rpm: Int) extends Feature {
    override def description = s"${bhp} bhp * ${rpm} rpm"
}

abstract sealed class Engine(val capacity: Int, val cylinders: Int) extends Feature {
    override def description = s"${capacity}L V${cylinders}"
}

final case class V8Engine(override val capacity: Int) extends Engine(capacity, 8)
final case class WankelRotaryEngine(override val capacity: Int) extends Engine(capacity, 6)

abstract sealed class Breaks(val ventilated: Boolean) extends Feature
final case class DiscBreaks(override val ventilated: Boolean) extends Breaks(ventilated) {
    override def description = "Disc breaks, " + (if (ventilated) "ventilated" else "not ventilated")
}

abstract sealed class Seat() extends Feature

case object RacingSeat extends Seat {
    override def description = "Racing seat"
}

final case class TouringSeat(armrest: Boolean, folding: Boolean) extends Seat {
    override def description =
        "Touring seat" +
            (if (armrest) " with armrest" else "") +
            (if (folding) ", folding" else "")
}

sealed class Window(val oneTouch: Boolean, val heated: Boolean) extends Feature {
    override def description =
        (if (heated) "Heated window" else "Window") +
            (if (oneTouch) ", with one-touch" else "")
}

object Window {
    def apply(oneTouch: Boolean, heated: Boolean) = new Window(oneTouch, heated)
}

final case class RearWindow(override val heated: Boolean) extends Window(false, heated) {
    override def description = if (heated) "Heated rear window" else "Rear window"
}

abstract sealed class Fuel() extends Feature
case object Petrol extends Fuel {
    override def description: String = "Petrol"
}
case object Diesel extends Fuel {
    override def description: String = "Diesel"
}

