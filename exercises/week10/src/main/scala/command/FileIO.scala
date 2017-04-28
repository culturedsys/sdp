package command

class FileIo(fileName: String) {
  def doFileIo() = println(s"Executing IO operations on $fileName...")
}

class FileIoJob(fileIo: FileIo) extends Job {
  def run() = fileIo.doFileIo()
}
