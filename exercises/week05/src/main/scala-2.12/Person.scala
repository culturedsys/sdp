/**
  * A person with a first and second name
  */
class Person(firstName: String, lastName: String) {

  def getFirstName = firstName
  def getLastName = lastName

  override def equals(other: Any) = {
    if (!other.isInstanceOf[Person])
      false
    else {
      val otherPerson = other.asInstanceOf[Person]
      firstName == otherPerson.getFirstName && lastName == otherPerson.getLastName
    }
  }
}

object Person {
  def apply(name: String) = {
    val names = name.split(" ")

    if (names.length != 2) {
      throw new IllegalArgumentException("Name must have exactly two components")
    }

    new Person(names(0), names(1))
  }
}
