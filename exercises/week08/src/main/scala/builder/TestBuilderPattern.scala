package builder

object TestBuilderPattern {
    def main(args: Array[String]) {
        var carBuilder: CarBuilder = SedanCarBuilder
        var director: CarDirector = CarDirector(carBuilder)

        director.build(List(
            Body(202, 76),
            Power(285, 6500),
            WankelRotaryEngine(3),
            DiscBreaks(ventilated = true),
            DiscBreaks(ventilated = true),
            DiscBreaks(ventilated =  false),
            DiscBreaks(ventilated =  false),
            TouringSeat(armrest = true, folding = false),
            TouringSeat(armrest = true, folding = false),
            TouringSeat(armrest = false, folding = true),
            TouringSeat(armrest = false, folding = true),
            Window(heated = false, oneTouch = true),
            Window(heated = false, oneTouch = true),
            Window(heated = false, oneTouch = false),
            Window(heated = false, oneTouch = false),
            Window(heated = true, oneTouch = false),
            Diesel
        ))
        println(carBuilder.getCar)

        carBuilder = SportsCarBuilder
        director = CarDirector(carBuilder)

        director.build(List(
            Body(192, 75),
            Power(323, 6800),
            V8Engine(4),
            DiscBreaks(ventilated = true),
            DiscBreaks(ventilated = true),
            RacingSeat,
            RacingSeat,
            Window(heated = true, oneTouch = true),
            Window(heated = true, oneTouch = true),
            Window(heated = true, oneTouch = false),
            Petrol
        ))
        println(carBuilder.getCar)
    }
}
