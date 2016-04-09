import fpinscala.state.RNG
import fpinscala.state.RNG._

val rng = Simple(986597542)

val p = RNG.double(rng)

RNG.double(p._2)._1
RNG.doubleWithMap(p._2)._1

RNG.intDouble(rng)._1
RNG.intDoubleWithMap(rng)._1
RNG.doubleInt(rng)._1
RNG.double3(rng)._1
RNG.ints(6)(rng)._1
RNG.intsWithSequence(6)(rng)._1
RNG.nonNegativeInt(rng)._1
RNG.nonNegativeIntWithMap(rng)._1

RNG.nonNegativeLessThan(6)(Simple(5))._1

RNG.rollDie(Simple(1))._1

RNG._map(RNG.ints(6))(a => a.sum)(rng)._1
RNG.map(RNG.ints(6))(a => a.sum)(rng)._1