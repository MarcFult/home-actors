package at.fhv.sysarch.lab2.homeautomation.ui;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import at.fhv.sysarch.lab2.homeautomation.devices.AirCondition;
import at.fhv.sysarch.lab2.homeautomation.devices.Blinds;
import at.fhv.sysarch.lab2.homeautomation.devices.TemperatureSensor;
import at.fhv.sysarch.lab2.homeautomation.messages.UserInput;

public class UI extends AbstractBehavior<UserInput> {

    private final ActorRef<TemperatureSensor.TemperatureCommand> tempSensor;
    private final ActorRef<AirCondition.AirConditionCommand> airCondition;
    private final ActorRef<Blinds.BlindsCommand> blinds;

    public static Behavior<UserInput> create(
            ActorRef<TemperatureSensor.TemperatureCommand> tempSensor,
            ActorRef<AirCondition.AirConditionCommand> airCondition,
            ActorRef<Blinds.BlindsCommand> blinds
    ) {
        return Behaviors.setup(context -> new UI(context, tempSensor, airCondition, blinds));
    }

    private UI(
            ActorContext<UserInput> context,
            ActorRef<TemperatureSensor.TemperatureCommand> tempSensor,
            ActorRef<AirCondition.AirConditionCommand> airCondition,
            ActorRef<Blinds.BlindsCommand> blinds
    ) {
        super(context);
        this.tempSensor = tempSensor;
        this.airCondition = airCondition;
        this.blinds = blinds;
        getContext().getLog().info("UI started");
    }

    @Override
    public Receive<UserInput> createReceive() {
        return newReceiveBuilder()
                .onMessage(UserInput.class, this::onUserInput)
                .onSignal(PostStop.class, signal -> onPostStop())
                .build();
    }

    private Behavior<UserInput> onUserInput(UserInput input) {
        String[] command = input.command.trim().split(" ");
        if (command.length == 2 && command[0].equalsIgnoreCase("t")) {
            try {
                double temp = Double.parseDouble(command[1]);
                tempSensor.tell(new TemperatureSensor.ReadTemperature(temp));
                getContext().getLog().info("Temperature input: {}", temp);
            } catch (NumberFormatException e) {
                getContext().getLog().warn("Invalid temperature value: {}", command[1]);
            }
        } else if (command.length == 2 && command[0].equalsIgnoreCase("blinds")) {
            if (command[1].equalsIgnoreCase("open")) {
                blinds.tell(new Blinds.PowerBlinds(true));
                getContext().getLog().info("Command sent: OPEN blinds");
            } else if (command[1].equalsIgnoreCase("close")) {
                blinds.tell(new Blinds.PowerBlinds(false));
                getContext().getLog().info("Command sent: CLOSE blinds");
            } else {
                getContext().getLog().warn("Invalid blinds command: {}", command[1]);
            }
        }
        else {
            getContext().getLog().warn("Unknown command: {}", input.command);
        }
        return this;
    }

    private UI onPostStop() {
        getContext().getLog().info("UI stopped");
        return this;
    }
}
