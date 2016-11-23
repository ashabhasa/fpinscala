package fpinscala.parsing

import fpinscala.testing._

import language.higherKinds

trait Parsers[Parser[+ _]] {
  self =>
  // so inner classes may call methods of trait

  def run[A](p: Parser[A])(input: String): Either[ParseError, A]

  def char(c: Char): Parser[Char] = string(c.toString).map(_.charAt(0))

  implicit def string(s: String): Parser[String]

  implicit def operators[A](p: Parser[A]): ParserOps[A] = ParserOps[A](p)

  implicit def asStringParser[A](a: A)(implicit f: A => Parser[String]): ParserOps[String] = ParserOps(f(a))

  def or[A](s1: Parser[A], s2: Parser[A]): Parser[A]

  def listOfN[A](n: Int, p: Parser[A]): Parser[List[A]]

  def succeed[A](a: A): Parser[A] = string("").map(_ => a)

  def many[A](p: Parser[A]): Parser[List[A]]

  def zeroOrMore[A](p: Parser[A]): Parser[Int] = map(many(p))(_.size)

  def oneOrMore[A](p: Parser[A]): Parser[Int]

  // A parser that recognizes zero or more 'a', followed by one or more 'b',
  // and which results in the pair of counts of characters seen.
  // For instance, given "bbb", we get (0,3), given "aaaab", we get (4,1), and so on
  def zAndOMore[A, B](p1: Parser[A], p2: Parser[B]): Parser[(A, B)] = {
    map2(p1, p2)((_, _))
  }

  def map[A, B](p: Parser[A])(f: A => B): Parser[B]

  def flatMap[A, B](p: Parser[A])(f: A => Parser[B]): Parser[B]

  //  def product[A, B](p: Parser[A], p2: Parser[B]): Parser[(A, B)] =

  def map2[A, B, C](p1: Parser[A], p2: Parser[B])(f: (A, B) => C): Parser[C] = for {
    a <- p1
    b <- p2
  } yield f(a, b)


  case class ParserOps[A](p: Parser[A]) {

    def |[B >: A](p2: Parser[B]): Parser[B] = self.or(p, p2)

    def or[B >: A](p2: Parser[B]): Parser[B] = self.or(p, p2)

    def map[B](f: A => B): Parser[B] = self.map(p)(f)

    def flatMap[B](f: A => Parser[B]): Parser[B] = self.flatMap(p)(f)

    def many(p: Parser[A]): Parser[List[A]] = self.many(p)
  }

  object Laws {

    def equal[A](p1: Parser[A], p2: Parser[A])(in: Gen[String]): Prop =
      Prop.forAll(in)(s => run(p1)(s) == run(p2)(s))

    def mapLaw[A](p: Parser[A])(in: Gen[String]): Prop =
      equal(p, p.map(a => a))(in)

  }

}

case class Location(input: String, offset: Int = 0) {

  lazy val line = input.slice(0, offset + 1).count(_ == '\n') + 1
  lazy val col = input.slice(0, offset + 1).reverse.indexOf('\n')

  def toError(msg: String): ParseError =
    ParseError(List((this, msg)))

  def advanceBy(n: Int) = copy(offset = offset + n)

  /* Returns the line corresponding to this location */
  def currentLine: String =
  if (input.length > 1) input.lines.drop(line - 1).next
  else ""
}

case class ParseError(stack: List[(Location, String)] = List(),
                      otherFailures: List[ParseError] = List()) {
}