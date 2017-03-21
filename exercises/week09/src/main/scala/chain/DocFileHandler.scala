package chain

class DocFileHandler(name: String) extends AbstractHandler(name) {
  override protected def processImpl(file: File): Boolean =
    if (file.fileType == "doc") {
      println(s"Process and saving ${file.fileType} file... by $getName")
      true
    } else {
      false
    }
}
