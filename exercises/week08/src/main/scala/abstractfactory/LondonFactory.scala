package abstractfactory

object LondonFactory extends AbstractParserFactory {
    class LondonError(message: String) extends Error {
        override def log: String = "London Error XML message: " + message
    }

    class LondonErrorParser extends XMLParser[LondonError] {
        override def parse(message: String): LondonError = new LondonError(message)
    }

    override def getErrorParser: XMLParser[LondonError] = new LondonErrorParser

    class LondonFeedback(message: String) extends Feedback {
        override def log: String = "London Feedback XML message: " + message
    }

    class LondonFeedbackParser extends XMLParser[LondonFeedback] {
        override def parse(message: String): LondonFeedback = new LondonFeedback(message)
    }

    override def getFeedbackParser: XMLParser[LondonFeedback] = new LondonFeedbackParser

    class LondonOrder(message: String) extends Order {
        override def log: String = "London Order XML message: " + message
    }

    class LondonOrderParser extends XMLParser[LondonOrder] {
        override def parse(message: String): LondonOrder = new LondonOrder(message)
    }

    override def getOrderParser: XMLParser[LondonOrder] = new LondonOrderParser

    class LondonResponse(message: String) extends Response {
        override def log: String = "London Response XML message: " + message
    }

    class LondonResponseParser extends XMLParser[LondonResponse] {
        override def parse(message: String): LondonResponse = new LondonResponse(message)
    }

    override def getResponseParser: XMLParser[Response] = new LondonResponseParser
}
