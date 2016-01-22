import fpinscala.datastructures.List

val l = List(1, 2, 3, 4, 5, 6)

val d: List[Int] = List.drop(l, 3)

List.dropWhile(d,(i:Int) => i % 2 == 0)
val dropwhile = List.dropWhile(l, (i:Int) => i % 2 == 0)