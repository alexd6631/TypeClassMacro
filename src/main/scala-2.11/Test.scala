object Test {
  import Implementations._

  def main(args: Array[String]): Unit = {

    val phones = Seq(AndroidPhone("archos"), IOSPhone("ipad"), AndroidPhone("foo"), Blackberry("bb"))
    phones foreach {
      p: Phone => println(p.toJson())
    }

  }
}


