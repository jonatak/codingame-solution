import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt
    val network = for(i <- 0 until n) yield {
        val Array(x, y) = readLine split " "
        (x.toLong, y.toLong)
    }
    
    def getSize(y: Long, mainCable: Long, cable: List[(Long, Long)]): Long = cable match {
        case Nil => y
        case (x1, y1) :: tail => getSize(y + abs(mainCable - y1), mainCable, tail)
    }
    
    val sorty = network.map(_._2).sorted
    
    val mainCable = sorty(sorty.size / 2)
    
    val diff = abs(network.map(_._1).max - network.map(_._1).min)
    println(diff + getSize(0, mainCable, network.toList))
}