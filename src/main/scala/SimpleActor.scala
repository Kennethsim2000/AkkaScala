import akka.actor.Actor

class SimpleActor extends Actor{

    override def receive: Receive = { // every actor needs to implement a receive method
        case s:String => println("String: " + s) // if we receive a string
    }
}
