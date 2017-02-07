/**
  * A person with a first and second name
  */
case class Person(firstName: String, lastName: String)

object Person {
  def apply(name: String) = {
    val names = name.split(" ")

    if (names.length != 2) {
      throw new IllegalArgumentException("Name must have exactly two components")
    }

    new Person(names(0), names(1))
  }
}
