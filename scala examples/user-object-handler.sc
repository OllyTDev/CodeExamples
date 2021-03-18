import scala.io.Source
import scala.collection.mutable
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object JsonUtil {
  var mapper: ScalaObjectMapper = new ObjectMapper() with ScalaObjectMapper {
    registerModule(DefaultScalaModule)
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  }

  def fromJson[T](json: String)(implicit m: Manifest[T]): T = {
    mapper.readValue[T](json)
  }
}
case class User(userID: String,
                username: String,
                permissions: Array[String] = Array())
{
  def getKey(rowKey:String):String = rowKey match {
    case "userId" => userID
    case "username" => username
  }
}

case class Users(user:User)

var list = mutable.MutableList[String]()

def readFile(pathToFile:String): String ={
  for(line<-Source.fromFile(pathToFile).getLines()){
    list += line
  }
  val s = list.mkString
  s

val path = "pathToFile/test.json"

val jsonString = readFile(path)
val users = JsonUtil.fromJson[Seq[User]](jsonString)
println(s"userId:" + users(1).getKey("userId"))
println(s"username:" + users(1).getKey("username"))