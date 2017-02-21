/**
  * A simple singleton that can be created immediately in the field declaration
  */
class SimpleSingleton private() {
  def getPi: Double = Math.PI
}

object SimpleSingleton {
  private val instance = new SimpleSingleton

  def getInstance: SimpleSingleton = instance
}

/**
  * A singleton class that is expensive to implement (because it calculates the first 100 Fibonacci numbers), so is not
  * instanciated directly in the field, but only on demand when getInstance is called.
  */
class ExpensiveSingleton private() {
  val fibonaccis = new Array[Int](100)

  fibonaccis(0) = 1
  fibonaccis(1) = 1

  for (i <- 2 to 99)
    fibonaccis(i) = fibonaccis(i - 1) + fibonaccis(i - 2)

  def getFibonacci(n: Int): Option[Int] =
    if (n < 0 || n >= 100)
      None
    else
      Some(fibonaccis(n))
}

object ExpensiveSingleton {
  private var instance: ExpensiveSingleton = null

  def getInstance: ExpensiveSingleton = {
    if (instance == null)
      instance = new ExpensiveSingleton

    instance
  }
}


/**
 * A singleton that can depend on a parameter, and so is not instanciated directly in the field, but instead is
 * instanciated in the getInstance method, after the parameter has had a chance to be set.
 */
class ParameterizedSingleton private(name: String) {
  def getName = name
}

object ParameterizedSingleton {
  private var instance: ParameterizedSingleton = null
  private var name: String = null

  /**
    * Specify the name to be used when creating the instance. If an instance has already been created, throws
    * IllegalStateException.
    *
    * @param name
    */
  def setName(name: String): Unit = {
    if (instance != null)
      throw new IllegalStateException("Instance has already been created")
    this.name = name
  }

  /**
    * Return the instance. If the name has not been set, throw IllegalStateException
    */
  def getInstance: ParameterizedSingleton = {
    if (instance == null)
      if (name == null)
        throw new IllegalStateException
      else
        instance = new ParameterizedSingleton(name)

    instance
  }
}
