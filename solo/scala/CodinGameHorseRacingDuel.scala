import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt
    
    val strength = for(i <- 0 until n) yield readInt
    
    case class Diff(s: Int, diff: Int)
    
    val d = strength.tail.sorted.par.foldLeft(Diff(strength.head, strength.head)) {
        case (left, right) => 
            val x = abs(left.s - right)
            Diff(right, if (x < left.diff) x else left.diff)
    }
    
    println(d.diff)
}