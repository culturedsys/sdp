package visitor

case class HtmlElement(var tagName: String) extends HtmlTag(tagName) {
  var tagBody: Option[String] = None

  override def setTagBody(tagBody: String): Unit = this.tagBody = Some(tagBody)
  override def generateHtml(): String =
    "<" + (tagName :: attributes).mkString(" ") + (tagBody match {
      case None => s" />"
      case Some(body) => s">$body</$tagName>"
    }) + "\n"

  override def accept(visitor: Visitor): Unit = visitor.visit(this)

  override def addChildTag(htmlTag: HtmlTag): Unit = ()
  override def removeChildTag(htmlTag: HtmlTag): Unit = ()
  override def getChildren(): List[HtmlTag] = Nil
}
