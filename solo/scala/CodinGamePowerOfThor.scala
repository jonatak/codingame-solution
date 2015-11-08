import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
object Player extends App {
    // lightx: the X position of the light of power
    // lighty: the Y position of the light of power
    // initialtx: Thor's starting X position
    // initialty: Thor's starting Y position
    val Array(lightx, lighty, initialtx, initialty) = for(i <- readLine split " ") yield i.toInt

    var Array(thorX, thorY) = Array(initialtx, initialty)
    // game loop
    while(true) {
        val remainingturns = readInt
        
        var move = ""
        
        if (thorY > lighty) {
            move = "N" 
            thorY = thorY - 1
        } else if (thorY < lighty) {
            move = "S"
            thorY = thorY + 1
        }
        
        if (thorX > lightx) {
            move = move + "W" 
            thorX = thorX - 1
        } else if (thorX < lightx) {
            move = move + "E"
            thorX = thorX + 1
        }
        // Write an action using println
        // To debug: Console.err.println("Debug messages...")
        
        println(move) // A single line providing the move to be made: N NE E SE S SW W or NW
    }
}