package at.fhv.sysarch.lab2.homeautomation.messages;

public class DeviceCommand {
    public final String targetDevice;
    public final String command;

    public DeviceCommand(String targetDevice, String command) {
        this.targetDevice = targetDevice;
        this.command = command;
    }
}