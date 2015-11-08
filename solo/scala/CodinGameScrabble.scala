import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt
    val dico = for(i <- 0 until n) yield readLine
    val letters = readLine
    
    val scoreLetter = Set(
        ("eaionrtlsu", 1),
        ("dg", 2),
        ("bcmp", 3),
        ("fhvwy", 4),
        ("k", 5),
        ("jx", 8),
        ("qz", 10)
    )
    
    def getWordScore(word: String): Int = {
        word.map { letter =>
            scoreLetter.find(_._1.contains(letter)).get._2
        }.sum
    }
    
    def containsMostOnce(word: List[Char], letter: List[Char]): Boolean = word match {
        case Nil => true
        case head :: tail if letter.contains(head) =>
            val (before, atAndAfter) = letter span (x => x != head)
            containsMostOnce(tail, before ::: atAndAfter.drop(1))
        case _ => false
    }
    
    val res = dico.filter(a => containsMostOnce(a.toList, letters.toList)).map { word =>
        (word, getWordScore(word))
    }.reduceLeft((a, b) => if (a._2 >= b._2) a else b)
    
    println(res._1)
}