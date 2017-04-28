package flyweight

abstract class PlatformImpl(name: String) extends Platform {
  println(s"${name}Platform object created")

  override def execute(code: Code): Unit =
    println(s"Compiling and executing $name code: ${code.code}")
}

class ScalaPlatform extends PlatformImpl("Scala")

class CPlatform extends PlatformImpl("C")

class JavaPlatform extends PlatformImpl("Java")

class RubyPlatform extends PlatformImpl("Ruby")

