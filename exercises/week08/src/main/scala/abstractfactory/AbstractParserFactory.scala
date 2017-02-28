package abstractfactory

/**
  * A factory which can produce the various sorts of parsers. I've altered this to have specific methods for each
  * type of parser, rather than one method producing a generic parser, because this improves type safety. This is in
  * line with how the abstract factory pattern is defined by Gamma et al., and the discussion of Scala
  * implementations of this pattern in Lokke.
  */
trait AbstractParserFactory {
    def getErrorParser: XMLParser[Error]
    def getFeedbackParser: XMLParser[Feedback]
    def getOrderParser: XMLParser[Order]
    def getResponseParser: XMLParser[Response]

    /**
      * Dynamically choose the type of parser based on the string supplied.
      *
      * @throws IllegalArgumentException if variety does not name a known parser type
      */
    def getParser(variety: String): XMLParser[BusinessObject] = variety match {
        case "ERROR" => getErrorParser
        case "FEEDBACK" => getFeedbackParser
        case "ORDER" => getOrderParser
        case "RESPONSE" => getResponseParser
        case _ => throw new IllegalArgumentException(s"Unknown parser type ${variety}")
    }

}