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

    def removeSommet(tree: ParMap[Int, Set[Int]]): ParMap[Int, Set[Int]] = {
        val sommet = tree.par.filter {
            case (a, b) => b.size == 1
        }.flatMap {
            case (a, b) => Set(a)
        }
        
        tree.par.filter {
            case (a, b) => b.size > 1
        }.flatMap {
            case (a, b) => Map(a -> (b -- sommet))
        }
    }
    
    def reduce(acc: Int, tree: ParMap[Int, Set[Int]]): Int = {
        if (tree.size > 1) reduce(acc + 1, removeSommet(tree.par))
        else acc
    }
    
    val tree: Map[Int, Set[Int]] = Map()
    
    for(i <- 0 until n) {
        val Array(x, y) = for (i <- readLine split " ") yield i.toInt
        tree.getOrElseUpdate(x, Set[Int]()) += y
        tree.getOrElseUpdate(y, Set[Int]()) += x
    }
    val res = reduce(0, tree.par)

    println(res) // The minimal amount of steps required to completely propagate the advertisement
}