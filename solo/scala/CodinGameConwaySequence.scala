import math._
import scala.util._
import scala.annotation.tailrec

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val r = readInt
    val l = readInt

    @tailrec
    def getSequence(acc: Int, result: List[Int], sequence: List[Int]): List[Int] = sequence match {
        case Nil if acc > 0 => getSequence(acc - 1, List(), result)
        case next if acc == 0 => sequence
        case next => 
        val (head, tail) = next.span(_ == next.head)
        getSequence(acc, result ::: List(head.size, head.head), tail)
    }
    println(getSequence(l - 1, List(), List(r)).mkString(" "))
}