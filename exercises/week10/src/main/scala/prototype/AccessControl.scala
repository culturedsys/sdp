package prototype

class AccessControl(controlLevel: String, access: String) {
  // Renamed from clone to avoid name clash with the dubious clone method on Object
  def duplicate(): AccessControl = new AccessControl(controlLevel, access)

  private var myControlLevel = controlLevel
  private var myAccess = access

  def getControlLevel: String = myControlLevel
  def setControlLevel(controlLevel: String): Unit = myControlLevel = controlLevel

  def getAccess: String = myAccess
  def setAccess(access: String): Unit = myAccess = access
}

object AccessControlProvider {
  private val defaults = Map(
    "USER" -> new AccessControl("USER", "DO_WORK"),
    "MANAGER" -> new AccessControl("MANAGER", "GENERATE/READ REPORTS")
  )

  def getAccessControlObject(level: String): AccessControl = defaults(level).duplicate()
}
