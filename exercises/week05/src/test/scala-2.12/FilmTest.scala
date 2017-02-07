import org.scalatest.FunSpec

/**
  * Tests for the Film class
  */
class FilmTest extends FunSpec {
  describe("Film") {
    val dob = 1980;
    val director = new Director("Alice", "Person", dob);
    val filmYear = 2000;
    val film = new Film("A Film in a Computer", filmYear, 8.9, director)


    it("can calculate the director's age") {
      assert(film.directorsAge == filmYear - dob)
    }

    it("returns true if it was directed by a given director") {
      assert(film.isDirectedBy(director))
    }

    it("returns false if it was not directed by a given director") {
      val otherDirector = new Director("Bob", "Someone", 1967)
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
  }
}
