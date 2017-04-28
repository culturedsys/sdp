package visitor

case class StyleVisitor() extends Visitor {
  override def visit(element: HtmlElement): Unit = element.setAttributes(element.getAttributes() :+ "style='width: 46px'")
  override def visit(element: HtmlParentElement): Unit = element.setAttributes(element.getAttributes() :+ "style='width: 58px'")
}
