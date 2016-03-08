import fpinscala.laziness.{Empty, Cons, Stream}


val s = Stream(1, 2, 3, 4, 5, 6)
s.toList
s.toList3
s.take(3).toList
s.take2(3).toList
s.take(1).toList
s.take(7).toList
s.takeWhile(i => i % 2 == 0).toList
s.forAll(i => i > 1)
s.drop(3).toList

val x = Stream.cons(println("Arber"),Stream(1+5,3+6))
val h1 = x.headOption2
val h2 = x.headOption2
s.headOption2
s.headOption
Empty.headOption
Stream.from(7).take(5).toList
Stream.fibs.take(10).toList
