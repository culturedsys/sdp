object LiveCoding {
  sealed trait TrafficLight {
    def next: TrafficLight = this match {
      case Red => Green
      case Green => Amber
      // case Amber => Red
    }
  }

  final case object Amber extends TrafficLight

  final case object Green extends TrafficLight

  final case object Red extends TrafficLight

  val light: TrafficLight = Red

  light.next

  light match {
    case Red => "Stop!"
    case Amber => "Wait..."
    case Green => "Go!"
  }

  sealed trait Food
  final case object Antelope extends Food
  final case object TigerFood extends Food
  final case object Licorice extends Food
  final case object Mice extends Food

  sealed trait Feline


  final case object Lion extends Feline
  final case object Tiger extends Feline
  final case object Panther extends Feline
  final case object Cat extends Feline


  object Dinner {
    def dinner(feline: Feline): Food = feline match {
      case Lion => Antelope
      case Tiger => TigerFood
      case Panther => Licorice
      case Cat => Mice
    }
  }

  Dinner.dinner(Lion)
}
