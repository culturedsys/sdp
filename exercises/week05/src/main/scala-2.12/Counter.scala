/**
  * A class encapsulating the behaviour of being incremented and decremented
  */
class Counter(value: Int) {
  def inc(increment: Int = 1): Counter = new Counter(count + increment)
  def dec(decrement: Int = 1): Counter = new Counter(count - decrement)

  def count = value
}
