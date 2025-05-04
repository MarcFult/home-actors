package at.fhv.sysarch.lab2.homeautomation.devices;

import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.javadsl.*;

import at.fhv.sysarch.lab2.homeautomation.messages.DeviceCommand;

public class AirCondition extends AbstractBehavior<AirCondition.AirConditionCommand> {

    // Define the command interface
    public interface AirConditionCommand {}

    // Accepts power control messages
    public static final class PowerAirCondition implements AirConditionCommand {
        public final boolean value;

        public PowerAirCondition(boolean value) {
            this.value = value;
        }
    }

    // Receives temperature updates
    public static final class EnrichedTemperature implements AirConditionCommand {
        public final double value;
        public final String unit;

        public EnrichedTemperature(double value, String unit) {
            this.value = value;
            this.unit = unit;
        }
    }

    // Wraps generic device commands
    public static final class WrappedDeviceCommand implements AirConditionCommand {
        public final DeviceCommand deviceCommand;

        public WrappedDeviceCommand(DeviceCommand deviceCommand) {
            this.deviceCommand = deviceCommand;
        }
    }

    private final String identifier;
    private boolean isOn = false;

    // Constructor
    public AirCondition(ActorContext<AirConditionCommand> context, String identifier) {
        super(context);
        this.identifier = identifier;
        context.getLog().info("AirCondition '{}' started", identifier);
    }

    public static Behavior<AirConditionCommand> create(String identifier) {
        return Behaviors.setup(context -> new AirCondition(context, identifier));
    }

    @Override
    public Receive<AirConditionCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(EnrichedTemperature.class, this::onTemperatureUpdate)
                .onMessage(PowerAirCondition.class, this::onPowerToggle)
                .onMessage(WrappedDeviceCommand.class, this::onDeviceCommand)
                .onSignal(PostStop.class, signal -> onPostStop())
                .build();
    }

    private Behavior<AirConditionCommand> onTemperatureUpdate(EnrichedTemperature msg) {
        getContext().getLog().info("[{}] Received temperature: {} {}", identifier, msg.value, msg.unit);

        // If the AC is on, simulate cooling
        if (isOn && msg.value > 24.0) {
            getContext().getLog().info("[{}] Cooling down... Temp is high!", identifier);
        } else if (!isOn) {
            getContext().getLog().info("[{}] AC is off. No action taken.", identifier);
        }

        return this;
    }

    private Behavior<AirConditionCommand> onPowerToggle(PowerAirCondition msg) {
        isOn = msg.value;
        getContext().getLog().info("[{}] Power toggled: {}", identifier, isOn ? "ON" : "OFF");
        return this;
    }

    private Behavior<AirConditionCommand> onDeviceCommand(WrappedDeviceCommand wrapper) {
        DeviceCommand cmd = wrapper.deviceCommand;

        // Only respond to commands intended for this device
        if (cmd.targetDevice.equalsIgnoreCase(identifier)) {
            if (cmd.command.equalsIgnoreCase("ON")) {
                return onPowerToggle(new PowerAirCondition(true));
            } else if (cmd.command.equalsIgnoreCase("OFF")) {
                return onPowerToggle(new PowerAirCondition(false));
            }
        }

        return this;
    }

    private AirCondition onPostStop() {
        getContext().getLog().info("AirCondition '{}' stopped", identifier);
        return this;
    }
}
