/*
package at.fhv.sysarch.lab2.homeautomation.messages;

public class EnvironmentMessages {

    public interface EnvironmentCommand {}

    // Temperature commands
    public static final class GetTemperature implements EnvironmentCommand {}
    public static final class UpdateTemperature implements EnvironmentCommand {
        public final double change;
        public UpdateTemperature(double change) {
            this.change = change;
        }
    }
    public static final class SetTemperature implements EnvironmentCommand {
        public final double temperature;
        public SetTemperature(double temperature) {
            this.temperature = temperature;
        }
    }

    // Temperature responses
    public static final class TemperatureResponse {
        public final double temperature;
        public TemperatureResponse(double temperature) {
            this.temperature = temperature;
        }
    }

    // Weather commands
    public static final class GetWeather implements EnvironmentCommand {}
    public static final class UpdateWeather implements EnvironmentCommand {
        public final WeatherCondition change;
        public UpdateWeather(WeatherCondition change) {
            this.change = change;
        }
    }
    public static final class SetWeather implements EnvironmentCommand {
        public final WeatherCondition weather;
        public SetWeather(WeatherCondition weather) {
            this.weather = weather;
        }
    }

    // Weather responses
    public static final class WeatherResponse {
        public final WeatherCondition weather;
        public WeatherResponse(WeatherCondition weather) {
            this.weather = weather;
        }
    }

    // Weather conditions
    public enum WeatherCondition {
        SUNNY, CLOUDY, RAINY, WINDY, SNOWY
    }

    // Simulation mode
    public static final class SetSimulationMode implements EnvironmentCommand {
        public final SimulationMode mode;
        public SetSimulationMode(SimulationMode mode) {
            this.mode = mode;
        }
    }

    public enum SimulationMode {
        INTERNAL, EXTERNAL, MANUAL
    }
}*/
