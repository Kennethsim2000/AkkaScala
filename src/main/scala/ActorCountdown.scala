import akka.actor.{ActorRef, ActorSystem, Props}

object ActorCountdown extends App {
    case class StartCounting(num: Int, other:ActorRef)
    case class Countdown(num: Int)
    val system = ActorSystem("system")
    val actor1 = system.actorOf(Props[CountDownActor], "countdownActor1")
    val actor2 = system.actorOf(Props[CountDownActor], "countdownActor2")
    actor1 ! StartCounting(10, actor2)

}
