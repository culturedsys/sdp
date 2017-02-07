import org.scalatest.FunSpec

/**
  * Tests for the Director class
  */
class DirectorTest extends FunSpec {
  describe("Director") {
    it("has a name") {
      val firstName = "Alice"
      val lastName = "Person"
      val director = new Director(firstName, lastName, 1980)
      assert(director.name == firstName + " " + lastName)
    }
  }
}
