/**
  * A class encapsulating the behaviour of being incremented and decremented
  */
case class Counter(count: Int = 0) {
  def inc(increment: Int = 1): Counter = copy(count = count + increment)
  def dec(decrement: Int = 1): Counter = copy(count = count - decrement)
}

class Adder(amount: Int) {
  def add(in: Int) = in + amount
}