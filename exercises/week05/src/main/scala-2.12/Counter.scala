/**
  * A class encapsulating the behaviour of being incremented and decremented
  */
class Counter(value: Int) {
  def inc: Counter = new Counter(count + 1)
  def dec: Counter = new Counter(count - 1)

  def count = value
}
