package askpattern

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object AskPattern extends App {
    case object AskName //when we have no parameters, we use a case object instead
    case class NameResponse(name: String)
    case class AskNameOf(other: ActorRef)

    val system = ActorSystem("system")
    val actor1 = system.actorOf(Props(new AskPatternActor("name")), "askActor1")
    val actor2 = system.actorOf(Props(new AskPatternActor("name2 ")), "askActor2")

    implicit val timeout = Timeout(1.seconds)
    val answer = actor1 ? AskName //import akka.pattern._ to import the ?
    //requires an implicit timeout variable
    // our answer here is a future,

    answer.foreach {
        case NameResponse(name) => println("name is " + name)
        case other => println("Unexpected response " + other)
    }

    actor1 ! AskNameOf(actor2)
    system.terminate()
}
