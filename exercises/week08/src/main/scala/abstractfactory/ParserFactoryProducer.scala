package abstractfactory

object ParserFactoryProducer {
    def getFactory(s: String): AbstractParserFactory = s match {
        case "NYCFactory" => NYCFactory
        case "LondonFactory" => LondonFactory
    }
}

