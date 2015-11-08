import math._
import scala.util._

object Solution extends App {
    val n = readInt // Number of elements which make up the association table.
    val q = readInt // Number Q of file names to be analyzed.
    
    val re = """\.([^\.]+)$""".r
    
    val mimes = (for(i <- 0 until n) yield {
        val Array(ext, mt) = readLine split " "
        (ext.toLowerCase, mt)
    }).toMap
    
    val files = for(i <- 0 until q) yield re.findAllIn(readLine).matchData.map{_.group(1).toLowerCase}.toList.headOption
    
    val extensions = files map {
        case Some(file) => mimes.get(file)
        case None => None
    }
    
    extensions.foreach { extOpt =>
        extOpt match {
            case Some(ext) => println(ext)
            case None => println("UNKNOWN") 
        }
    }
    
}