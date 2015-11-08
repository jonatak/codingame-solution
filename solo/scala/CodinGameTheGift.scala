import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt
    val c = readInt
    
    val budgets = (for(i <- 0 until n) yield readInt).sorted
    val total = budgets.sum
    
    def calcBudget(cot: Int, rem: Int, b: List[Int]): List[Int] = b match {
        case Nil => List()
        case head :: Nil => List(rem)
        case head :: tail if head == 0 => head :: calcBudget(cot, rem, tail)
        case head :: tail if cot == head => head :: calcBudget(cot, rem - cot, tail)
        case head :: tail => 
            if ((rem / cot + rem % cot) == (tail.size + 1)) {
                cot :: calcBudget(cot, rem - cot, tail)
            } else {
                val deno = List(rem / (tail.size + 1), head).min
                deno :: calcBudget(deno, rem - deno, tail)
            }

    }
    
    if (total < c) {
        println("IMPOSSIBLE")
    } else {
        val res = calcBudget(1, c, budgets.toList)
        res.foreach(println)
    }
}