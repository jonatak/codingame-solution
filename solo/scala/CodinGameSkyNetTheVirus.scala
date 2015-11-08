import math._
import scala.util._
import scala.collection.parallel.immutable.ParSet
import scala.annotation.tailrec

object Player extends App {
    // n: the total number of nodes in the level, including the gateways
    // l: the number of links
    // e: the number of exit gateways
    val Array(n, l, e) = for(i <- readLine split " ") yield i.toInt
    
    var inputPath = (for(i <- 0 until l) yield {
        var Array(a, b) = readLine split " "
        (a.toInt, b.toInt)
    }).toSet

    val wayout = for(i <- 0 until e) yield readInt

    def getUnderGraph(w: Int, g: Set[(Int, Int)]): ParSet[(Int,Int)] = {
        val n = g.par.map { 
            case (x: Int, y: Int) => 
                if (x == w) y
                else if (y == w) x
        }
        g.par.filter { case (x: Int, y: Int) => 
            n.contains(y) || n.contains(x)
        }
    }

    case class Intersect(res: Set[(Int, Int)], last: ParSet[(Int, Int)])

    @tailrec
    def process(path: Set[(Int, Int)]): Unit = {
        val si = readInt // The index of the node on which the Skynet agent is positioned this turn
        
        val col = path.par.filter { case (x: Int, y: Int) => 
                (x == si && wayout.contains(y)) || (y == si && wayout.contains(x))
        }
        
        lazy val int = wayout.map { w =>
            getUnderGraph(w, path)
        }.foldLeft(Intersect(Set(), getUnderGraph(si, path))) {
            case (Intersect(acc, last), elem1) => Intersect(acc ++ elem1.intersect(last), elem1)
        }
        
        lazy val int2 = int.res.filter {
            case (x: Int, y: Int) => x == si || y == si
        }
        
        lazy val solution = col.headOption orElse int2.headOption orElse int.res.headOption orElse path.headOption
        
        solution match {
            case Some((x:Int, y:Int)) =>
                println(s"$x $y")
                process(path - Tuple2(x, y))
            case None =>
                println("0 0")
        }
    }
    process(inputPath)
}