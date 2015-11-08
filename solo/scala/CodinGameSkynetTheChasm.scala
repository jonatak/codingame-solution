import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Player extends App {
    val road = readInt // the length of the road before the gap.
    val gap = readInt // the length of the gap.
    val platform = readInt // the length of the landing platform.
    
    while(true) {
        val speed = readInt // the motorbike's speed.
        val coordx = readInt // the position on the road of the motorbike.
        
        if (coordx >=( road + gap)) println("SLOW")
        else if ((coordx + speed) >= (road + gap)) println("JUMP")
        else if (speed > (gap + 1)) println("SLOW")
        else if (speed < (gap + 1)) println("SPEED")
        else println("WAIT")
    }
}
