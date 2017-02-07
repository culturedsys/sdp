/**
  * A class representing information about a director
  */
case class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def name = firstName + " " + lastName
}

object Director {
  def older(first: Director, second: Director) =
    if (first.yearOfBirth < second.yearOfBirth)
      first
    else
      second
}
