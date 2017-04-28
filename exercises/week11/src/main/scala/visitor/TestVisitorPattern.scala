package visitor

object TestVisitorPattern extends App {
  println("Befor visitor......... \n")

  val parentTag: HtmlTag = HtmlParentElement("html")

  val p1: HtmlTag = HtmlParentElement("body")

  parentTag.addChildTag(p1)

  val child1: HtmlTag = HtmlElement("p")

  child1.setTagBody("Testing html tag library")
  p1.addChildTag(child1)

  val child2 = HtmlElement("p")
  child2.setTagBody("Paragraph 2")

  p1.addChildTag(child2)

  println(parentTag.generateHtml())

  println("\nAfter visitor....... \n")

  val cssClass: Visitor = CssClassVisitor()
  val style: Visitor = StyleVisitor()

  parentTag.accept(style)
  parentTag.accept(cssClass)

  p1.accept(style)
  p1.accept(cssClass)

  child1.accept(style)
  child1.accept(cssClass)

  child2.accept(style)
  child2.accept(cssClass)

  println(parentTag.generateHtml())
}
