import scala.collection.mutable.Map
import scala.collection.mutable.Set

object Solution extends App {
    val n = readInt
    val tree: Map[Int, Set[Int]] = Map()
    
    for(i <- 0 until n) {
        val Array(x, y) = for (i <- readLine split " ") yield i.toInt
        tree.getOrElseUpdate(x, Set()) += y
    }
    
    def explore(acc: Int, leaf: Set[Int]) :Int = {
        if (leaf.size == 0) acc
        else {
            val next: Set[Int] = leaf.flatMap { node =>
                tree.getOrElse(node, Set())
            }
            explore(acc + 1, next)
        }
    }
    val res = tree.map { elem =>
        explore(0, elem._2)
    }.max
    
    println(res + 1)
}
