import math._
import scala.util._
import scala.annotation.tailrec

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Player extends App {
    val surfacen = readInt // the number of points used to draw the surface of Mars.

    type Coord = Tuple2[Int, Int]

    @tailrec
    def findFlatFloor(map: List[Coord]): (Coord, Coord) = map match {
        case first :: second :: Nil => (first, second)
        case first :: second :: tail =>
          if (first._2 == second._2 && first._1 + 1000 <= second._1)
            (first, second)
          else
            findFlatFloor(second :: tail)
        case Nil => ((0, 0), (0, 0))
    }

    def checkRighSpeed(speed: Int): Boolean = speed >= -40 && speed <= 40

    def checkInZone(current: Coord, lspace1: Coord, lspace2: Coord) = {
        current._1 >= lspace1._1 && current._1 <= lspace2._1
    }

    val map = (for(i <- 0 until surfacen) yield {
        // landx: X coordinate of a surface point. (0 to 6999)
        // landy: Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
        val Array(landx, landy) = for(i <- readLine split " ") yield i.toInt
        (landx, landy)
    }).toList

    val (fp1, fp2) = findFlatFloor(map)
    // game loop
    while(true) {
        // hspeed: the horizontal speed (in m/s), can be negative.
        // vspeed: the vertical speed (in m/s), can be negative.
        // fuel: the quantity of remaining fuel in liters.
        // rotate: the rotation angle in degrees (-90 to 90).
        // power: the thrust power (0 to 4).
        val Array(x, y, hspeed, vspeed, fuel, rotate, power) = for(i <- readLine split " ") yield i.toInt

        if (checkRighSpeed(hspeed) && !checkInZone((x, y), fp1, fp2)) {
            if (x >= fp2._1 && hspeed < 40) println("45 4")
            else println("-45 4")
        } else  if ((hspeed < -60 || hspeed > 60) && !checkInZone((x, y), fp1, fp2)) {
            if (hspeed > 40) println("45 4")
            else println("-45 4")
        }else if (checkInZone((x, y), fp1, fp2)) {
            if (hspeed < -10) println("-45 4")
            else if (hspeed > 10) println("45 4")
            else if (vspeed > -30) println("0 3")
            else println("0 4")
        } else if (vspeed < 0) println("0 4")
        else println("0 3")
    }
}
