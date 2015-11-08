import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Solution extends App {
    val n = readInt // the number of temperatures to analyse
    val temps = readLine // the n temperatures expressed as integers ranging from -273 to 5526
    
    if (n == 0) println(0)
    else if (n == 1) {
        println(temps)   
    } else  {
        println(temps.split(" ").map {_.toInt}.reduce { (acc, elem) => (
            if (abs(elem) == abs(acc)) {
                if (elem > acc) elem
                else acc
            }
            else if (abs(elem) < abs(acc)) elem
            else acc
        )})
    }
    // Write an action using println
    // To debug: Console.err.println("Debug messages...")
}