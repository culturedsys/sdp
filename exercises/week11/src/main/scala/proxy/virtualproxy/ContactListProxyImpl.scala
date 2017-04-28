package proxy.virtualproxy

import scala.collection.mutable.ListBuffer

class ContactListProxyImpl extends ContactList {

  private lazy val contactList: ContactList = new ContactListImpl

  override def employeeList: ListBuffer[Employee] = contactList.employeeList

}
