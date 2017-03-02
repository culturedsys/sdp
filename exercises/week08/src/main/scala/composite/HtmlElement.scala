package composite

import java.util.Collections
import java.util

case class HtmlElement(s: String) extends HtmlTag(s) {
  var body: String = _

  override def generateHtml: Unit = {
    print(startTag)
    print(body)
    println(endTag)
  }

  override def setTagBody(tagBody: String): Unit = body = tagBody

  override def removeChildTag(htmlTag: HtmlTag): Unit = ()

  override def addChildTag(htmlTag: HtmlTag): Unit = ()

  override def getChildren: util.List[HtmlTag] = Collections.emptyList[HtmlTag]
}
