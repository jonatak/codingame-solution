import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt
    val vs = readLine.split(" ").map(_.toInt).toList
   
    def calc(h: List[(Int, Int)], high: Int, low: Int, remain: List[Int]): Int = remain match {
        case Nil => ((high, low) :: h).map(a => a._1 - a._2).max * -1
        case head :: tail if head < low => calc(h, high, head, tail)
        case head :: tail if head > high => calc((high, low) :: h, head, head, tail)
        case head :: tail => calc(h, high, low, tail)
    }
    
    println(calc(List(), vs.head, vs.head, vs.tail))
}