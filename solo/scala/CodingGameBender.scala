import math._
import scala.util._
import scala.annotation.tailrec

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
case class Direction(x: Int, y: Int, label: String)
case class State(direction: Direction, inverted: Boolean, casseur: Boolean, alive: Boolean)

object Direction {
  val SOUTH = Direction(1, 0, "SOUTH")
  val EAST = Direction(0, 1, "EAST")
  val NORTH = Direction(-1, 0, "NORTH")
  val WEST = Direction(0, -1, "WEST")
}

import Direction._

class Bender(var x: Int, var y: Int) {

    var state = State(SOUTH, false, false, true)

    def choose(env: Char, direction: Direction) = env match {
        case 'X' if state.casseur == true => state = state.copy(direction = direction); true
        case 'I' => state = state.copy(inverted = !state.inverted, direction = direction); true
        case 'B' => state = state.copy(casseur = !state.casseur, direction = direction); true
        case '$' => state = state.copy(alive = false, direction = direction); true
        case 'S' => state = state.copy(direction = SOUTH); true
        case 'N' => state = state.copy(direction = NORTH); true
        case 'E' => state = state.copy(direction = EAST); true
        case 'W' => state = state.copy(direction = WEST); true
        case 'X' => false
        case '#' => false
        case _ => state = state.copy(direction = direction); true
    }
}

object Solution extends App {
    val normal = List(SOUTH, EAST, NORTH, WEST)
    val invert = List(WEST, NORTH, EAST, SOUTH)

    val Array(l, c) = for(i <- readLine split " ") yield i.toInt
    var map = (for(i <- 0 until l) yield readLine).toList.zipWithIndex.map {case (a,b) => (b, a)}.toMap

    val tel = map collect { case (a, b) if b.indexOf('T') != -1 => (a, b.indexOf('T')) }

    val teleporter = tel.toSeq.zip(tel.toSeq.reverse).toMap

    val (startx, starty) = map.collectFirst { case (a, b) if b.indexOf('@') != -1 => (a, b.indexOf('@')) }.get

    val bender = new Bender(startx, starty)
    map = map + (startx -> map(startx).updated(starty, ' ').toString)

    def getPosibility(x: Int, y:Int) = map(x + 1)(y) :: map(x)(y + 1) :: map(x - 1)(y) ::  map(x)(y - 1) :: Nil

    def associatePosWithDir(pos: List[Char], inverted: Boolean) = {
        if (inverted == true) pos.toSeq.reverse.toList.zip(invert)
        else pos.zip(normal)
    }

    def getElemAtCoord(coord: Direction, x: Int, y: Int) = map(x + coord.x)(y + coord.y)

    @tailrec
    def processLoop(bender: Bender, result: List[(Int, Int, State, Direction, Char)]): List[(Int, Int, State, Direction, Char)] = bender.state.alive match {
        case true =>
            val posibility = (getElemAtCoord(bender.state.direction, bender.x, bender.y), bender.state.direction) :: associatePosWithDir(getPosibility(bender.x, bender.y), bender.state.inverted)
            val (elem, direction): Tuple2[Char, Direction] = posibility.collectFirst {
                case (elem, direction) if (bender.choose(elem, direction) == true) => (elem, direction)
            }.get
            if (elem == 'T') {
                val (nx, ny) = teleporter((bender.x + direction.x, bender.y + direction.y))
                bender.x = nx
                bender.y = ny
            } else {
                bender.x = bender.x + direction.x
                bender.y = bender.y + direction.y
            }
            if (elem == 'X') map = map + (bender.x -> map(bender.x).updated(bender.y, ' ').toString)
            val step = (bender.x, bender.y, bender.state, direction, elem)

            if (result.count(_ == step) > 5 ) List()
            else processLoop(bender, step :: result)
        case false => result
    }

    val result = processLoop(bender, List())
    if (result.size == 0) {
        println("LOOP")
    } else {
        result.reverse.foreach {
          case (x, y, state, direction, m) => println(direction.label)
      }
    }
}
