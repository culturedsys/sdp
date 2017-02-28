package builder

trait CarBuilder {
  def buildBodyStyle(body: Body)

  def buildPower(power: Power)

  def buildEngine(engine: Engine)

  def buildBreaks(breaks: Breaks)

  def buildSeats(seat: Seat)

  def buildWindows(window: Window)

  def buildFuelType(fuel: Fuel)

  def getCar: Car
}