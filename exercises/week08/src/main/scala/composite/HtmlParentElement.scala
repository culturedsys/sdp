package composite
import java.util

case class HtmlParentElement(s: String) extends HtmlTag(s) {
  override def generateHtml: Unit = {
    println(startTag)
    getChildren.forEach(_.generateHtml)
    println(endTag)
  }

  var children: util.List[HtmlTag] = new util.ArrayList[HtmlTag]()

  override def removeChildTag(htmlTag: HtmlTag): Unit = children.remove(htmlTag)

  override def addChildTag(htmlTag: HtmlTag): Unit = children.add(htmlTag)

  override def getChildren: util.List[HtmlTag] = children

  override def setTagBody(tagBody: String): Unit = ()
}
