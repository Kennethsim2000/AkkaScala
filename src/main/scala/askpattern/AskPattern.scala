package askpattern

import akka.actor.{ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object AskPattern extends App {
    case object AskName //when we have no parameters, we use a case object instead
    case class NameResponse(name: String)

    val system = ActorSystem("system")
    val actor = system.actorOf(Props(new AskPatternActor("name")), "askActor1")
    implicit val timeout = Timeout(1.seconds)
    val answer = actor ? AskName //import akka.pattern._ to import the ?
    //requires an implicit timeout variable
    // our answer here is a future,

    answer.foreach {
        case NameResponse(name) => println("name is " + name)
        case other => println("Unexpected response " + other)
    }
    system.terminate()
}
