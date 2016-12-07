import math._
import scala.util._

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

object Direction extends Enumeration {
    type Direction = Value
    val TOP, RIGHT, BOTTOM, LEFT = Value
}

import Direction._

object Player extends App {
    val Rooms: List[Map[Direction, Direction]] = List(
        Map(),
        Map(TOP -> BOTTOM, LEFT -> BOTTOM, RIGHT -> BOTTOM),
        Map(LEFT -> RIGHT, RIGHT -> LEFT),
        Map(TOP -> BOTTOM),
        Map(TOP -> LEFT, RIGHT -> BOTTOM),
        Map(TOP -> RIGHT, LEFT -> BOTTOM),
        Map(LEFT -> RIGHT, RIGHT -> LEFT),
        Map(TOP -> BOTTOM, RIGHT -> BOTTOM),
        Map(LEFT -> BOTTOM, RIGHT -> BOTTOM),
        Map(TOP -> BOTTOM, LEFT -> BOTTOM),
        Map(TOP -> LEFT),
        Map(TOP -> RIGHT),
        Map(RIGHT -> BOTTOM),
        Map(LEFT -> BOTTOM)
    )
    // w: number of columns.
    // h: number of rows.
    val Array(w, h) = for(i <- readLine split " ") yield i.toInt
    val map = (for(i <- 0 until h) yield readLine.split(" ").map(_.toInt).toList).toList
    val ex = readInt // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

    // game loop
    while(true) {
        val Array(_xi, _yi, pos) = readLine split " "
        val xi = _xi.toInt
        val yi = _yi.toInt

        val out = Rooms(map(yi)(xi))(Direction.withName(pos))

        out match {
            case TOP => println(s"${xi} ${yi - 1}")
            case BOTTOM => println(s"${xi} ${yi + 1}")
            case LEFT => println(s"${xi - 1} ${yi}")
            case RIGHT => println(s"${xi + 1} ${yi}")
        }
    }
}
