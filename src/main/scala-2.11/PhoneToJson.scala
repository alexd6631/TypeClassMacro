import scala.annotation.implicitNotFound

@implicitNotFound("Missing PhoneToJson implementation for ${P}")
trait PhoneToJson[P <: Phone] {
  def toJson(p: P): String
}

object Implementations {
  implicit val androidToJson = new PhoneToJson[AndroidPhone] {
    override def toJson(p: AndroidPhone): String = "Android phone"
  }

  implicit val iosToJson = new PhoneToJson[IOSPhone] {
    override def toJson(p: IOSPhone): String = "IOS phone"
  }

  implicit val bbToJson = new PhoneToJson[Blackberry] {
    override def toJson(p: Blackberry): String = "Blackberry phone"
  }

  implicit val phoneToJson = new PhoneToJson[Phone] {
    val tcFinder = MyMacros.typeclassFinder[Phone, PhoneToJson]
    override def toJson(p: Phone): String = tcFinder(p).toJson(p)
  }


  implicit class RichPhone[P <: Phone : PhoneToJson](val p: P) {
    def toJson() = implicitly[PhoneToJson[P]].toJson(p)
  }
}