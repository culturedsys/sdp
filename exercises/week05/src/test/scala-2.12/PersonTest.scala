import org.scalatest.FunSpec

/**
  * Tests for the Person class and its companion object
  */
class PersonTest extends FunSpec {
  describe("Person") {
    it("can be created with a name") {
      assert(Person("Alice Person") == Person("Alice", "Person"))
    }

    it("throws when given more than two names") {
      intercept[IllegalArgumentException] {
        Person("Alice Patronymic Persona")
      }
    }

    it("throws when given fewer than two names") {
      intercept[IllegalArgumentException] {
        Person("Alice")
      }
    }
  }
}
