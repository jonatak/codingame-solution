import math._
import scala.util._
import scala.collection.parallel.mutable.ParMap
import scala.collection.mutable.Map
import scala.collection.mutable.Set

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt // the number of adjacency relations
    val tree: Map[Int, Set[Int]] = Map()
    val explored = Set[Int]()
    
    for(i <- 0 until n) {
        val Array(x, y) = for (i <- readLine split " ") yield i.toInt
        tree.getOrElseUpdate(x, Set()) += y
        tree.getOrElseUpdate(y, Set()) += x
    }
    
    val (key, first) = tree.find(_._2.size == 1) get
    
    def explore(acc: Int, leaf: Set[Int]) :Int = {
        if (leaf.size == 0) acc
        else {
            val next: Set[Int] = leaf.flatMap { node =>
                tree(node).filter(!explored.contains(_))
            }
            explored ++= next
            explore(acc + 1, next)
        }
    }
    val res = explore(0, first)
    println(res / 2 + res % 2)
}