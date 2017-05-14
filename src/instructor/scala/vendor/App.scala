package vendor

object App{
  def main(args:Array[String]):Unit={
    print("hello")

    val names = Vector("iconst", "iadd", "isub", "imul", "idiv", "irem",
      "ineg", "iinc", "idec", "idup", "iswap", "print")

    /**
      * A map from bytecode names to a unique byte that represents them.
      */
    val bytecode = names.zip(1.to(names.length).map(_.toByte)).toMap
    bytecode.foreach(x=>println(x._1+" => "+x._2))
  }
}