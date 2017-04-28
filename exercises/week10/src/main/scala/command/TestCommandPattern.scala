package command

object TestCommandPattern extends App {

  val threadCount = 10
  val jobCount = 5

  val pool = new ThreadPool(threadCount)

  for (i <- 0.until(jobCount)) {
    val emailJob = new EmailJob(new Email("somebody@example.com"))
    val smsJob = new SmsJob(new Sms("555-555-555"))
    val fileIOJob = new FileIoJob(new FileIo("/tmp/example.txt"))
    val logJob = new LogJob(new Log("WARN"))
    pool.addJob(emailJob)
    pool.addJob(smsJob)
    pool.addJob(fileIOJob)
    pool.addJob(logJob)
  }
  pool.shutdownPool()
}

