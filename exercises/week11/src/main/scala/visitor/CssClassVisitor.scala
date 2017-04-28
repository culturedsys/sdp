package visitor

case class CssClassVisitor() extends Visitor {
  def doVisit(element: HtmlTag): Unit = element.setAttributes(element.getAttributes() :+ "class='visitor'")
  override def visit(element: HtmlElement): Unit = doVisit(element)
  override def visit(element: HtmlParentElement): Unit = doVisit(element)
}
