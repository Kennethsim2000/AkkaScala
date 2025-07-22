package askpattern

import akka.actor.Actor
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import askpattern.AskPattern.{AskName, AskNameOf, NameResponse}

import scala.concurrent.Future
import scala.concurrent.duration.{DurationDouble, DurationInt}
import scala.util.{Failure, Success}

class AskPatternActor(val name:String) extends Actor{

    override def receive: Receive = {
        case AskName => sender ! NameResponse(name)
        case AskNameOf(other) =>
            implicit val timeout = Timeout(1.seconds)
            val f = other ? AskName
            f.onComplete {
                case Success(NameResponse(name)) =>
                    println("They said their name was " + name)
                case Success(n) => println("They didnt tell us their name")
                case Failure(ex) => println("Asking their name failed")
            }
            val currentSender = sender
            Future { // we use a future if we want the actor to do something else and not wait for this
                // for futures like this, since the actor may receive multiple messages, it is advisable
                // to store the currentSender in a variable first, to preserve the sender we want to send the message to.
                currentSender ! "message"
            }
    }
}
