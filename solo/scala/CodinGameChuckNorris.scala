import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val message: String = readLine
   
    def unaryChar(res: List[List[Char]], remain: List[Char]): List[List[Char]] = {
        remain match {
            case Nil => res
            case x :: xs => {
                var (a, b) = xs.span(_ == x)
                unaryChar(res ::: List(x::a), b)
            }
        }
    }

    val res = unaryChar(List(), message.getBytes.map(_.toInt.toBinaryString).map { elem =>
         if (elem.length < 7) List.fill(7 - elem.length)('0') ::: elem.toCharArray.toList
         else elem.toCharArray.toList
    }.flatten.toList).map{ x =>
        if (x.head == '1') List("0", x.map(a => "0").mkString(""))
        else List("00", x.mkString(""))
    }.flatten.mkString(" ")
    
    println(res)
}