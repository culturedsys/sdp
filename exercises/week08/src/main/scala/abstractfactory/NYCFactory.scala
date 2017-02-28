package abstractfactory

object NYCFactory extends AbstractParserFactory {
    class NYCError(message: String) extends Error {
        def log = "NYC Error XML message: " + message
    }

    class NYCErrorParser extends XMLParser[NYCError] {
        override def parse(message: String): NYCError = new NYCError(message)
    }

    override def getErrorParser: XMLParser[NYCError] = new NYCErrorParser

    class NYCFeedback(message: String) extends Feedback {
        override def log: String = "NYC Feedback XML message: " + message
    }

    class NYCFeedbackParser extends XMLParser[NYCFeedback] {
        override def parse(message: String): NYCFeedback = new NYCFeedback(message)
    }

    override def getFeedbackParser: XMLParser[NYCFeedback] = new NYCFeedbackParser

    class NYCOrder(message: String) extends Order {
        override def log: String = "NYC Order XML message: " + message
    }

    class NYCOrderParser extends XMLParser[NYCOrder] {
        override def parse(message: String): NYCOrder = new NYCOrder(message)
    }

    override def getOrderParser: XMLParser[NYCOrder] = new NYCOrderParser

    class NYCResponse(message: String) extends Response {
        override def log: String = "NYC Response XML message: " + message
    }

    class NYCResponseParser extends XMLParser[NYCResponse] {
        override def parse(message: String): NYCResponse = new NYCResponse(message)
    }

    override def getResponseParser: XMLParser[Response] = new NYCResponseParser
}
