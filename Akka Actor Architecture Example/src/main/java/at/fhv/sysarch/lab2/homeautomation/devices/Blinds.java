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
        public final boolean value;

        public PowerBlinds(boolean value) {
            this.value = value;
        }
    }

    public static final class BlindsStatus implements BlindsCommand {
        public final boolean value;

        public BlindsStatus(boolean value) {
            this.value = value;
        }
    }

    private boolean isOpen = false;

    public static Behavior<BlindsCommand> create(String identifier) {
        return Behaviors.setup(Blinds::new);
    }

    private Blinds(ActorContext<BlindsCommand> context) {
        super(context);
        getContext().getLog().info("Blinds started");
    }

    @Override
    public Receive<BlindsCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(PowerBlinds.class, this::onPowerBlinds)
                .onSignal(PostStop.class, signal -> onPostStop())
                .build();
    }

    private Behavior<BlindsCommand> onPowerBlinds(PowerBlinds command) {
        this.isOpen = command.value;
        getContext().getLog().info("Blinds are now {}", isOpen ? "OPEN" : "CLOSED");
        return this;
    }

    private Blinds onPostStop() {
        getContext().getLog().info("Blinds stopped");
        return this;
    }
}
