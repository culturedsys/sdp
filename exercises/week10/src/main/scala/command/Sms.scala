package command

class Sms(phoneNumber: String) {
  def sendSms() = println(s"Sending SMS to $phoneNumber...")
}

class SmsJob(sms: Sms) extends Job {
  def run() = sms.sendSms()
}
