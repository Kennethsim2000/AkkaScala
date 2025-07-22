import akka.actor.{ActorSystem, Props}

object SimpleActorExample extends App {
    val system = ActorSystem("SimpleSystem")
    val actor = system.actorOf(Props[SimpleActor], "SimpleActor")
    actor ! "Hi there." // this is used to send a message to the actor
    actor ! 42
}
