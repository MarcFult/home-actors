/*
package at.fhv.sysarch.lab2.homeautomation.environment;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import at.fhv.sysarch.lab2.homeautomation.messages.EnvironmentMessages;

import java.time.Duration;
import java.util.Random;

public class TemperatureSimulator extends AbstractBehavior<EnvironmentMessages.EnvironmentCommand> {

    private double currentTemperature;
    private final Random random = new Random();

    private TemperatureSimulator(ActorContext<EnvironmentMessages.EnvironmentCommand> context, double initialTemp) {
        super(context);
        this.currentTemperature = initialTemp;

        // Schedule periodic temperature updates
        context.getSystem().scheduler().scheduleAtFixedRate(
                Duration.ofSeconds(5),
                Duration.ofSeconds(10),
                () -> context.getSelf().tell(new EnvironmentMessages.UpdateTemperature(random.nextDouble() * 1.0 - 0.5)),
                context.getSystem().executionContext()
        );
    }

    public static Behavior<EnvironmentMessages.EnvironmentCommand> create(double initialTemp) {
        return Behaviors.setup(context -> new TemperatureSimulator(context, initialTemp));
    }

    @Override
    public Receive<EnvironmentMessages.EnvironmentCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(EnvironmentMessages.GetTemperature.class, this::onGetTemperature)
                .onMessage(EnvironmentMessages.UpdateTemperature.class, this::onUpdateTemperature)
                .onMessage(EnvironmentMessages.SetTemperature.class, this::onSetTemperature)
                .build();
    }

    private Behavior<EnvironmentMessages.EnvironmentCommand> onGetTemperature(EnvironmentMessages.GetTemperature get) {
        getContext().getSender().tell(new EnvironmentMessages.TemperatureResponse(currentTemperature));
        return this;
    }

    private Behavior<EnvironmentMessages.EnvironmentCommand> onUpdateTemperature(EnvironmentMessages.UpdateTemperature update) {
        currentTemperature += update.change;
        getContext().getLog().info("Temperature updated to: {}", currentTemperature);
        return this;
    }

    private Behavior<EnvironmentMessages.EnvironmentCommand> onSetTemperature(EnvironmentMessages.SetTemperature set) {
        currentTemperature = set.temperature;
        getContext().getLog().info("Temperature set to: {}", currentTemperature);
        return this;
    }
}*/
