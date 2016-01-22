import fpinscala.datastructures.{Cons, List, Nil}

val l = List(1, 2, 3, 4, 5, 6)

val d: List[Int] = List.drop(l, 3)

List.dropWhile(d)(i => i % 2 == 0)
val dropwhile = List.dropWhile(l)(i => i % 2 == 0)

List.setHead(l, 9)


List.init(l)

List.foldRight(List(1,2,3,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,3,3,2,24,6,7,2,3,4,6),Nil:List[Int]) (Cons(_,_))

List.length(d)

List.foldLeft(l,0)((a,b) => a + b)


List.sum3(l)
List.prod3(l)