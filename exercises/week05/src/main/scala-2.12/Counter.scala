/**
  * A class encapsulating the behaviour of being incremented and decremented
  */
case class Counter(count: Int = 0) {
  def inc(increment: Int = 1): Counter = new Counter(count + increment)
  def dec(decrement: Int = 1): Counter = new Counter(count - decrement)
}
