import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val l = readInt
    val h = readInt
    val t = readLine
    
    val a:Int = 'a'.toInt
    val z:Int = 'z'.toInt
    
    val A:Int = 'A'.toInt
    val Z:Int = 'Z'.toInt
    
    for(i <- 0 until h) {
        val row = readLine
        val res = row.grouped(l).toList
        val line = t.getBytes.map(_.toInt).map { x =>
            if (x >= a && x <= z) res(x.toInt - a)
            else if (x >= A && x <= z) res(x.toInt - A)
            else res.last
        }
        println(line.mkString(""))
    }
}