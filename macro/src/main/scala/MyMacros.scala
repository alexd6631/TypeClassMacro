import scala.language.higherKinds
import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros

object MyMacros {
  def typeclassFinder[A, T[_ <: A]]: PartialFunction[A, T[A]] = macro MyMacros.typeclassFinderImpl[A, T[A]]

  def typeclassFinderImpl[A: c.WeakTypeTag, T: c.WeakTypeTag](c: Context) = {
    import c.universe._
    val cls = weakTypeOf[A].typeSymbol.asClass
    val tc = weakTypeOf[T].typeSymbol
    require(cls.isSealed)
    cls.typeSignature
    val cases = cls.knownDirectSubclasses map {
      sub =>
        val tcInst = tq"$tc[$sub]"
        cq"_ : $sub => implicitly[$tcInst].asInstanceOf[$tc[$cls]]"
    }
    q"{ case ..$cases }"
  }
}

