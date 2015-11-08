import math._
import scala.util._


object Solution extends App {
    val lon = readLine.replaceAll(",", ".").toDouble
    val lat = readLine.replaceAll(",", ".").toDouble
    val n = readInt
    
    def sqr(x: Double) = x * x
    
    def distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double = {
        val x = (lon2 - lon1) * cos((lat1 + lat2) / 2)
        val y = lat2 -lat1
        sqrt(sqr(x) + sqr(y)) * 6371
    }
    
    case class Location(id: Integer, name: String, address: String, contact:String, lon: Double, lat: Double)
    case class Distance(location: Location, distance: Double)
    
    val location = for(i <- 0 until n) yield {
        val line = readLine.split(";")
        Location(line(0).toInt, line(1), line(2), line(3), line(4).replaceAll(",", ".").toDouble, line(5).replaceAll(",", ".").toDouble)
    }

    val last = location.par.map { l =>
        Distance(l, distance(lat, lon, l.lat, l.lon))
    } .reduce { (a, b) =>
        if (a.distance < b.distance) a
        else b
    }

   println(last.location.name)
}