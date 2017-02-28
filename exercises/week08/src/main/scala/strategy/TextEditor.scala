package strategy

case class TextEditor(formatter: TextFormatter) {
  def publishText(s: String) = println(s"[${formatter.getClass.getSimpleName}] ${formatter.format(s)}")
}
