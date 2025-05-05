package at.fhv.sysarch.lab2.homeautomation.devices;

import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Blinds extends AbstractBehavior<Blinds.BlindsCommand> {
    public interface BlindsCommand {}

    public static final class PowerBlinds implements BlindsCommand {
        final Boolean value;
        public PowerBlinds(Boolean value) {this.value = value;}
    }

    public static final class BlindsStatus implements BlindsCommand {
        Boolean value;

        public BlindsStatus(Boolean value) {this.value = value;}
    }

    public Blinds(ActorContext<BlindsCommand> context) {
        super(context);
        getContext().getLog().info("Blinds started");
    }

    public static Behavior<Blinds.BlindsCommand> create(String identifier) {
        return Behaviors.setup(context -> new Blinds(context));
    }

    @Override
    public Receive<Blinds.BlindsCommand> createReceive() {
        return newReceiveBuilder()

                //TODO onMESSAGE
                .onSignal(PostStop.class, signal -> onPostStop())
                .build();

    }
    private Blinds onPostStop(){
        getContext().getLog().info("post stop");
        return this;
    }

}
