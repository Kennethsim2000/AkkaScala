import ActorCountdown.{Countdown, StartCounting}
import akka.actor.{Actor, ActorRef}

class CountDownActor extends Actor{
    // stores the time to countdown and a reference to the actor you are going to be counting with
    override def receive: Receive = {
        case StartCounting(num, other) =>
            println(num)
            other ! Countdown(num - 1) // send the countdown - 1 message to the other actor ref
        case Countdown(num) =>
            if(num > 0) {
                println(num)
                sender ! Countdown(num - 1) // send the countdown message back to the sender
            } else {
                context.system.terminate()
            }
    }
}
