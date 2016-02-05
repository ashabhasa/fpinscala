import fpinscala.errorhandling.{None, Option, Some}
import scala.{Either => _, Option => _, Some => _}
val lo: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4), Some(5))
Option.sequence(lo)
