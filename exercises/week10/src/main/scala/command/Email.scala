package command

class Email(address: String) {
  def sendEmail() = println(s"Sending email to $address...")
}

class EmailJob(email: Email) extends Job {
  def run() = email.sendEmail()
}
