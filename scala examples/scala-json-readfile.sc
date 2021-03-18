import org.mortbay.util.ajax.JSON
import scala.io.Source
import scala.collection.mutable

def parseJson(file: String): Object ={
  JSON.parse(file)
}

var list = mutable.MutableList[String]()

def readFile(path:String): String ={
  for(line<-Source.fromFile(path).getLines()){
    println(line)
    list += line
  }
  val s = list.mkString
  s
}
val path = "pathToFile/test.json"
parseJson(readFile(path))
