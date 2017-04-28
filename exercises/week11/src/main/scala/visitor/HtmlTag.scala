package visitor

abstract class HtmlTag(tagName: String) extends Element {
  def getTagName(): String = tagName

  var attributes: List[String] = Nil

  def setAttributes(attributes: List[String]): Unit = this.attributes = attributes
  def getAttributes(): List[String] = attributes

  def setTagBody(tagBody: String): Unit
  def addChildTag(htmlTag: HtmlTag): Unit
  def removeChildTag(htmlTag: HtmlTag): Unit
  def getChildren(): List[HtmlTag]
  def generateHtml(): String
}
