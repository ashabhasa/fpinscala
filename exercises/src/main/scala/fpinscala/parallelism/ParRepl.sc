import java.util.concurrent.Executors

import fpinscala.parallelism.Par

def inc(x: Int) = x + 1

val es = Executors.newFixedThreadPool(1)

//val asInc = Par.asyncF(inc)(1)
//
//val p = asInc(es).get()
//
//
//val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
//
//val fList = Par.parFilter(list)(a => a % 2 == 0)(es).get()

val a = Par.lazyUnit(42 + 1)

//println(Par.equal(es)(a, Par.fork(a)))
println(Par.equal(es)(a, Par.fork2(a)))