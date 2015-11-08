import math._
import scala.util._

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
object Player extends App {
    val width = readInt // the number of cells on the X axis
    val height = readInt // the number of cells on the Y axis
    
    val tab = for(i <- 0 until height) yield readLine.toCharArray.toList

    val graphByColumn = tab.transpose map {
        _.zipWithIndex.toList
    }
    
    val graphByLine = tab map(_.zipWithIndex.toList)
    
    def nbottom(numline: Int, col: List[(Char, Int)]): String= col match {
        case (char, index)::tail if char == '0' => s" $numline $index"
        case (char, index)::tail => nbottom(numline, col.dropWhile(t => t._1 == '.'))
        case Nil => " -1 -1"
    }
    
    def nright(numcol: Int, line: List[(Char, Int)]): String = line match {
        case (char, index)::tail if char == '0' => s" $index $numcol"
        case (char, index)::tail => nright(numcol, line.dropWhile(t => t._1 == '.'))
        case Nil => " -1 -1"
    }
    
    def walk(numcol:Int, line: List[(Char, Int)]): List[String] = line match {
        case (char, index)::tail if char == '0' =>
            val col = graphByColumn.lift(index) match {
                case Some(elem) =>
                    nbottom(index, elem.drop(numcol + 1))
                case None => 
                    " -1 -1"
            }
            List(s"$index $numcol" + nright(numcol, tail) + col) ::: walk(numcol, tail.dropWhile(t => t._1 == '.'))
        case (char, index)::tail if char == '.' => walk(numcol, tail.dropWhile(t => t._1 == '.'))
        case Nil => List()
    }
    
    graphByLine.zipWithIndex.toList map {
        case (line: List[(Char, Int)], index: Int) =>
            walk(index, line).filter(_.size > 0).foreach {println}
    }
}