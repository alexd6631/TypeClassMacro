

sealed trait Phone
case class AndroidPhone(name: String) extends Phone
case class IOSPhone(name: String) extends Phone
case class Blackberry(name: String) extends Phone

