import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Player extends App {

    // game loop
    while(true) {
        val Array(spacex, spacey) = for(i <- readLine split " ") yield i.toInt
        val mountains = for(i <- 0 until 8) yield readInt
        if (mountains(spacex) == mountains.max) {
            println("FIRE")
        } else {
            println("HOLD")
        }
        // Write an action using println
        // To debug: Console.err.println("Debug messages...")
        
        //println("HOLD") // either:  FIRE (ship is firing its phase cannons) or HOLD (ship is not firing).
    }
}