/**
  * A class representing information about a director
  */
class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def name = firstName + " " + lastName
}

object Director {
  def apply(firstName: String, lastName: String, yearOfBirth: Int) =
    new Director(firstName, lastName, yearOfBirth)

  def older(first: Director, second: Director) =
    if (first.yearOfBirth < second.yearOfBirth)
      first
    else
      second
}
