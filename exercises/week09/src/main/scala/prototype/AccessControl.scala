package prototype

class AccessControl(controlLevel: String, access: String) {
  override def clone() = new AccessControl(controlLevel, access)

  private var myControlLevel = controlLevel
  private var myAccess = access

  def getControlLevel = myControlLevel
  def setControlLevel(controlLevel: String) = myControlLevel = controlLevel

  def getAccess = myAccess
  def setAccess(access: String) = myAccess = access
}

object AccessControlProvider {
  private val defaults = Map(
    "USER" -> new AccessControl("USER", "DO_WORK"),
    "MANAGER" -> new AccessControl("MANAGER", "GENERATE/READ REPORTS")
  )

  def getAccessControlObject(level: String) = defaults(level).clone()
}
