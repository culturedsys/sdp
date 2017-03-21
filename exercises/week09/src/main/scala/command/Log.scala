package command

class Log(level: String) {
  def writeLog() = println(s"Logging at level $level...")
}

class LogJob(log: Log) extends Job {
  def run() = log.writeLog()
}

