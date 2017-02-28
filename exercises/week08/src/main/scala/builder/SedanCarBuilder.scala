package builder

/**
  * Build a car with sedan constraints and defaults
  */
object SedanCarBuilder extends CarBuilder {
    private var body: Body = null
    private var power: Power = null
    private var engine: Engine = null
    private var breaks: List[Breaks] = Nil
    private var seats: List[Seat] = Nil
    private var windows: List[Window] = Nil
    private var fuel: Fuel = null

    override def buildBodyStyle(body: Body): Unit = this.body = body

    override def buildPower(power: Power): Unit = this.power = power

    override def buildEngine(engine: Engine): Unit = this.engine = engine

    /**
      * Can add up to four sets of breaks, the rest are ignored
      */
    override def buildBreaks(breaks: Breaks): Unit =
        if (this.breaks.length < 4)
            this.breaks = breaks :: this.breaks

    /**
      * Can add up to five seats, the rest are ignored
      */
    override def buildSeats(seat: Seat): Unit =
        if (this.seats.length < 5)
            this.seats = seat :: this.seats

    /**
      * Can add up to six windows, the rest are ignored
      */
    override def buildWindows(window: Window): Unit =
        if (this.windows.length < 6)
            this.windows = window :: this.windows

    override def buildFuelType(fuel: Fuel): Unit = this.fuel = fuel

    override def getCar: Car = {
        val car = Car("Sedan")

        if (body == null)
            throw new IllegalStateException("No body specified")
        car.setBodyStyle(body.description)

        if (power == null)
            throw new IllegalStateException("No power specified")
        car.setPower(power.description)

        if (engine == null)
            throw new IllegalStateException("No engine specified")
        car.setEngine(engine.description)

        val defaultBreaks = List(DiscBreaks(ventilated = true),
            DiscBreaks(ventilated = true),
            DiscBreaks(ventilated = true),
            DiscBreaks(ventilated = true))
        breaks = (breaks ++ defaultBreaks).take(4)
        car.setBreaks(breaks.map(_.description).mkString("; "))

        val defaultSeats = List(
            TouringSeat(folding = true, armrest = true),
            TouringSeat(folding = true, armrest = true),
            TouringSeat(folding = true, armrest = true),
            TouringSeat(folding = true, armrest = true)
        )
        seats = (seats ++ defaultSeats).take(5)
        car.setSeats(seats.map(_.description).mkString("; "))

        val defaultWindows = List(
            Window(heated = false, oneTouch = true),
            Window(heated = false, oneTouch = true),
            Window(heated = false, oneTouch = true),
            Window(heated = false, oneTouch = true),
            RearWindow(heated = true)
        )
        windows = (windows ++ defaultWindows).take(6)
        car.setWindows(windows.map(_.description).mkString("; "))

        if (fuel == null)
            fuel = Diesel
        car.setFuelType(fuel.description)

        car
    }
}
