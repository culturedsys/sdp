package abstractfactory

/**
  * Represents some kind of business object
  */
trait BusinessObject {
    /**
      * Provide a message of some sort describing the object
      */
    def log: String
}

trait Error extends BusinessObject
trait Feedback extends BusinessObject
trait Order extends BusinessObject
trait Response extends BusinessObject