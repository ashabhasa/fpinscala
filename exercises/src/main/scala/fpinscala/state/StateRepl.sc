import fpinscala.state.RNG
import fpinscala.state.RNG._

val rng = Simple(986597542)

val p = RNG.double(rng)
RNG.double(p._2)._1

RNG.intDouble(rng)._1
RNG.doubleInt(rng)._1
RNG.double3(rng)._1
RNG.ints(6)(rng)._1
RNG.nonNegativeEven.apply(rng)._1


