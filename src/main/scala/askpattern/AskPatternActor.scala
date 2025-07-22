package askpattern

import akka.actor.Actor
import askpattern.AskPattern.{AskName, NameResponse}

class AskPatternActor(val name:String) extends Actor{

    override def receive: Receive = {
        case AskName => sender ! NameResponse(name)
    }
}
