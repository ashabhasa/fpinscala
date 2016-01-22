import fpinscala.datastructures.{Cons, List, Nil}

val l = List(1, 2, 3, 4, 5, 6)

val d: List[Int] = List.drop(l, 3)

List.dropWhile(d)(i => i % 2 == 0)
val dropwhile = List.dropWhile(l)(i => i % 2 == 0)

List.setHead(l, 9)


List.init(l)


List.length(d)

List.foldLeft(l,0)((a,b) => a + b)


List.sum3(l)
List.prod3(l)

List.reverse(l)
List.reverse2(l)
List.foldRightViaFoldLeft(l,0)(_+_)

List.appendViaFoldRight(l,List(9,7))

List.concatenate(List(List(1,2,3,4,5), List(9,8,7,6)))

List.addOne(l)