package at.fhv.sysarch.lab2.homeautomation.messages;

public class WeatherUpdate {
    public final double temperature;
    public final boolean sunny;

    public WeatherUpdate(double temperature, boolean sunny) {
        this.temperature = temperature;
        this.sunny = sunny;
    }
}