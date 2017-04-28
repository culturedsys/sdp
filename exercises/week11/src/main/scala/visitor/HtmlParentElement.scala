package visitor

case class HtmlParentElement(var tagName: String) extends HtmlTag(tagName) {
  private var childrenTag: List[HtmlTag] = Nil

  override def addChildTag(htmlTag: HtmlTag): Unit = childrenTag = childrenTag :+ htmlTag
  override def removeChildTag(htmlTag: HtmlTag): Unit = childrenTag = childrenTag.filter(_ != htmlTag)
  override def getChildren(): List[HtmlTag] = childrenTag
  override def generateHtml(): String =
    "<" + (tagName :: attributes).mkString(" ") + ">\n" + childrenTag.map(_.generateHtml()).mkString + s"</$tagName>\n"
  override def accept(visitor: Visitor): Unit = visitor.visit(this)

  override def setTagBody(tagBody: String): Unit = ()
}
