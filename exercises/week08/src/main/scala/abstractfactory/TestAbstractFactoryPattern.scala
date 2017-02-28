package abstractfactory

object TestAbstractFactoryPattern {
    def main(args: Array[String]) {
        var parserFactory: AbstractParserFactory = ParserFactoryProducer.getFactory("NYCFactory")
        val orderParser: XMLParser[Order] = parserFactory.getOrderParser
        val order = orderParser.parse("Some message")
        println(order.log)
        println("************************************")
        parserFactory = ParserFactoryProducer.getFactory("LondonFactory")
        val feedbackParser = parserFactory.getFeedbackParser
        val feedback = feedbackParser.parse("Another message")
        println(feedback.log)

        val parser = parserFactory.getParser("RESPONSE")
        println(parser.parse("Further messages").log)
    }
}