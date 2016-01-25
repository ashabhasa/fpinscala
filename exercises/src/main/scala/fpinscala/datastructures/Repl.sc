import fpinscala.datastructures.List

val l = List(1, 2, 3, 4, 5, 6)

val d: List[Int] = List.drop(l, 3)

def isEven(i: Int): Boolean = i % 2 == 0
List.dropWhile(l)(isEven)

List.setHead(l, 9)

List.init(l)

List.length(d)

List.foldLeft(l, 0)((a, b) => a + b)

List.sum3(l)
List.prod3(l)

List.reverse(l)
List.reverse2(l)
List.foldRightViaFoldLeft(l, 0)(_ + _)

List.appendViaFoldRight(l, List(9, 7))

List.concatenate(List(List(1, 2, 3, 4, 5), List(9, 8, 7, 6)))

List.addOne(l)

List.map(l)(_ + 1)

List.filter(l)(isEven)

List.flatMap(List(1,2,3))(i => List(i,i))
List.flatMap2(List(1,2,3))(i => List(i,i))
List.flatMap3(List(1,2,3))(i => List(i,i))

List.filter2(l)(isEven)

List.addTwoLists(l,l)

List.mapLists(l,l)(_ + _)
List.mapLists(l,l)(_ * _)

List.hasSubsequence(List(1, 2, 3, 4, 5),List(2,3))
List.hasSubsequence(List(1, 2, 3, 4, 5),List(1))
List.hasSubsequence(List(1, 2, 3, 4, 5),List(2,3,4))
List.hasSubsequence(List(1, 2, 3, 4, 5),List(2,5))
List.hasSubsequence(List(1, 2, 3, 4, 5),List(2,4))
List.hasSubsequence(List(1, 2, 3, 4, 5),List(4))
