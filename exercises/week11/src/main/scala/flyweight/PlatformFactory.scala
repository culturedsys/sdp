package flyweight

object PlatformFactory {

  private val map: Map[String, Platform] = Map(
    "C" -> new CPlatform,
    "JAVA" -> new JavaPlatform,
    "SCALA" -> new ScalaPlatform,
    "RUBY" -> new RubyPlatform
  )

  def getPlatformInstance(platformType: String): Platform = map.getOrElse(platformType, throw new IllegalArgumentException)
}
