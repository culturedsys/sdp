import org.scalatest.FunSpec

/**
  * Tests for the Film class
  */
class FilmTest extends FunSpec {
  describe("Film") {
    val dob = 1980;
    val director = Director("Alice", "Person", dob);
    val filmYear = 2000;
    val film = Film("A Film in a Computer", filmYear, 8.9, director)


    it("can calculate the director's age") {
      assert(film.directorsAge == filmYear - dob)
    }

    it("returns true if it was directed by a given director") {
      assert(film.isDirectedBy(director))
    }

    val otherDirector = Director("Bob", "Someone", 1967)

    it("returns false if it was not directed by a given director") {
      assert(!film.isDirectedBy(otherDirector))
    }

    it("can copy with no changes") {
      assert(film.copy() == film)
    }

    it("produces a copy with a changed name") {
      val newName = "A different film"
      val copy = film.copy(name=newName)
      assert(copy.name == newName)
    }

    val otherFilm = Film("Un Chien Andalou", 1970, 5.0, otherDirector)

    it("can choose the highest rated") {
      assert(Film.highestRating(film, otherFilm) == film)
    }

    it("can find the oldest director") {
      assert(Film.oldestDirectorAtTheTime(film, otherFilm) == director)
    }
  }
}
