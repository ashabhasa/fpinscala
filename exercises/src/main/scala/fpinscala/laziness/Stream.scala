package fpinscala.laziness

import Stream._

trait Stream[+A] {

  def foldRight[B](z: => B)(f: (A, => B) => B): B = // The arrow `=>` in front of the argument type `B` means that the function `f` takes its second argument by name and may choose not to evaluate it.
    this match {
      case Cons(h, t) => f(h(), t().foldRight(z)(f)) // If `f` doesn't evaluate its second argument, the recursion never occurs.
      case _ => z
    }

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b) // Here `b` is the unevaluated recursive step that folds the tail of the stream. If `p(a)` returns `true`, `b` will never be evaluated and the computation terminates early.

  @annotation.tailrec
  final def find(f: A => Boolean): Option[A] = this match {
    case Empty => None
    case Cons(h, t) => if (f(h())) Some(h()) else t().find(f)
  }

  def toList: List[A] = foldRight[List[A]](Nil)((a, b) => a :: b)

  def toList2: List[A] = this match {
    case Cons(h, t) => h() :: t().toList2
    case Empty => Nil
  }

  def toList3: List[A] = {
    @annotation.tailrec
    def go(stream: Stream[A], acc: List[A]): List[A] = stream match {
      case Cons(h, t) => go(t(), h() :: acc)
      case _ => acc.reverse
    }
    go(this, Nil)
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1 => cons(h(), t().take(n - 1))
    case Cons(h, _) if n == 1 => cons(h(), empty)
    case _ => empty
  }

  /**
    * trying to write a tail recursive version. but the result is reversed. :(
    *
    * @param n
    * @return
    */
  def take2(n: Int): Stream[A] = {
    @annotation.tailrec
    def go(n: Int, stream: Stream[A], acc: Stream[A]): Stream[A] = stream match {
      case Cons(h, t) if n > 0 => go(n - 1, t(), cons(h(), acc))
      case _ => acc
    }
    go(n, this, Empty)
  }

  @annotation.tailrec
  final def drop(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1 => t().drop(n - 1)
    case Cons(h, t) if n == 1 => t()
    case _ => this
  }

  def takeWhile(p: A => Boolean): Stream[A] =
    foldRight[Stream[A]](empty)((a, b) => if (p(a)) cons(a, b) else b)

  def takeWhile2(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t().takeWhile2(p))
    case _ => empty
  }

  def forAll(p: A => Boolean): Boolean = foldRight(true)((a, b) => p(a) && b)

  def headOption: Option[A] = sys.error("todo")

  def headOption2: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

  // 5.7 map, filter, append, flatmap using foldRight. Part of the exercise is
  // writing your own function signatures.

  def startsWith[B](s: Stream[B]): Boolean = sys.error("todo")
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))

  val ones: Stream[Int] = Stream.cons(1, ones)

  def from(n: Int): Stream[Int] = sys.error("todo")

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = sys.error("todo")
}