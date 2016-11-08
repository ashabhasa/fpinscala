import java.util.concurrent.Executors

import fpinscala.parallelism.Par

def inc(x: Int) = x + 1

val asInc = Par.asyncF(inc)(1)

val es = Executors.newFixedThreadPool(2)

val p = asInc(es).get()