package composite

import java.util

abstract class HtmlTag(tagName: String) {
  var startTag: String = _
  var endTag: String = _

  def getTagName: String = tagName

  def setStartTag(tag: String) = startTag = tag

  def setEndTag(tag: String) = endTag = tag

  def setTagBody(tagBody: String)

  def addChildTag(htmlTag: HtmlTag)

  def removeChildTag(htmlTag: HtmlTag)

  def getChildren: util.List[HtmlTag]

  def generateHtml
}