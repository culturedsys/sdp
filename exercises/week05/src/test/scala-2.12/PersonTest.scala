import org.scalatest.FunSpec

/**
  * Tests for the Person class and its companion object
  */
class PersonTest extends FunSpec {
  describe("Person") {
    it("can be created with a name") {
      assert(Person("Alice Person") == new Person("Alice", "Person"))
    }
  }
}
