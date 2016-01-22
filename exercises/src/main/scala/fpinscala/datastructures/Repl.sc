import fpinscala.datastructures.List

val l = List(1, 2, 3, 4, 5, 6)

val d: List[Int] = List.drop(l, 3)

List.dropWhile(d)(i => i % 2 == 0)
val dropwhile = List.dropWhile(l)(i => i % 2 == 0)

List.setHead(l, 9)


List.init(l)
