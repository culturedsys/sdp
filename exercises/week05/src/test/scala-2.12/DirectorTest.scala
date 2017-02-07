import org.scalatest.FunSpec

/**
  * Tests for the Director class
  */
class DirectorTest extends FunSpec {
  describe("Director") {

    val firstName = "Alice"
    val lastName = "Person"
    val director = Director(firstName, lastName, 1980)

    it("has a name") {
      assert(director.name == firstName + " " + lastName)
    }

    it("can calculate which of two is older") {
      val otherDirector = Director("Bob", "Nobody", 1960)
      assert(Director.older(director, otherDirector) == otherDirector)
    }
  }
}
