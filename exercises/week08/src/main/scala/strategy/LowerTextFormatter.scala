package strategy

case class LowerTextFormatter() extends TextFormatter {
  override def format(text: String): String = text.toLowerCase
}
