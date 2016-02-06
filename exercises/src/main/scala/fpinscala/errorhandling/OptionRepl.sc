import fpinscala.errorhandling.{Option, Some}
import scala.{Either => _, Option => _, Some => _}
val lo: List[Option[Int]] = List(Some(1), Some(2), Some(3), Some(4), Some(5))
Option.sequence2(lo)

val li = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

Option.traverse(li)(i => Some(i + 1))
Option.traverse2(li)(i => Some(i + 1))

