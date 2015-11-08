import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 
case class PS(digit: Int, next: List[PS])
 
object Solution extends App {
    val n = readInt
    
    def createStruct(elem: List[Int]): PS = elem match {
        case head :: Nil => PS(head, List())
        case head :: tail => PS(head, List(createStruct(tail)))
    }
    
    def mergePhoneList(ps: List[PS]): List[PS] = ps match {
        case Nil => List()
        case head :: midle :: tail if head.digit == midle.digit => mergePhoneList(mergePhone(head, midle) :: tail)
        case head :: tail => head :: mergePhoneList(tail)
    }
    
    def mergePhone(ps1: PS, ps2: PS): PS = {
        PS(ps1.digit, mergePhoneList((ps1.next ::: ps2.next).sortWith(_.digit > _.digit)))
    }
    
    val phones = for(i <- 0 until n) yield readLine
    
    val sortedPhones = phones.sorted.map(a => createStruct(a.map(b => b.asDigit).toList)).toList

    val tree = mergePhoneList(sortedPhones)    

    def getRes(tree: List[PS]) : Int = tree match {
        case Nil => 0
        case head :: tail => 1 + getRes(head.next) + getRes(tail)
    }
    
    println(getRes(tree)) // The number of elements (referencing a number) stored in the structure.
}