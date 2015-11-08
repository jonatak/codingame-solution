object ChuckNorris extends App {
    val message: String = readLine
    
    val res = message.split(" ").grouped(14).toList.map { elem =>
        elem.grouped(2).toList.map {
            case Array("0", x) => ("1", x.length)
            case Array("00", x) => ("0", x.length)
        }.flatMap {
            case (x, y) => List.fill(y)(x)
        }.mkString("")
    }.flatten.grouped(7).toList.map{
        ch => Integer.parseInt(ch.mkString(""), 2)
    }.map(_.toChar).mkString("")
    
    println(res)
}