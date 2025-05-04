package at.fhv.sysarch.lab2.homeautomation.messages;

public class SensorUpdate {
    public final String sensorType;
    public final double value;

    public SensorUpdate(String sensorType, double value) {
        this.sensorType = sensorType;
        this.value = value;
    }
}