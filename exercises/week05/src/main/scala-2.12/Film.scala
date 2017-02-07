/**
  * A class representing information about a film
  */
class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director) {
  def directorsAge: Int = yearOfRelease - director.yearOfBirth

  def isDirectedBy(query: Director): Boolean = query == director

  def copy(name: String = name,
           yearOfRelease: Int = yearOfRelease,
           imdbRating: Double = imdbRating,
           director: Director = director) =
    new Film(name, yearOfRelease, imdbRating, director)

  override def equals(other: Any): Boolean = other match {
    case otherFilm: Film =>
      name == otherFilm.name &&
      yearOfRelease == otherFilm.yearOfRelease &&
      imdbRating == otherFilm.imdbRating &&
      director == otherFilm.director
    case _ => false
  }
}

object Film {
  def apply(name: String, yearOfRelease: Int, imdbRating: Double, director: Director) =
    new Film(name, yearOfRelease, imdbRating, director)

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