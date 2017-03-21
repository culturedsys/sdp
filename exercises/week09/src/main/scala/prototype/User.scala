package prototype

class User(name: String, level: String, accessControl: AccessControl) {
  def getUserName = name
  def getAccessControl = accessControl

  override def toString = s"Name: $getUserName, Level: $level, Access Control Level: ${getAccessControl.getControlLevel}, Access: ${getAccessControl.getAccess}"
}
