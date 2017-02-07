/**
  * A class representing information about a film
  */
case class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director) {
  def directorsAge: Int = yearOfRelease - director.yearOfBirth

  def isDirectedBy(query: Director): Boolean = query == director
}

object Film {
  def highestRating(first: Film, second: Film) =
    if (first.imdbRating > second.imdbRating)
      first
    else
      second

  def oldestDirectorAtTheTime(first: Film, second: Film) =
    if (first.directorsAge > second.directorsAge)
      first.director
    else
      second.director
}