/*
package at.fhv.sysarch.lab2.homeautomation.environment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import at.fhv.sysarch.lab2.homeautomation.messages.EnvironmentMessages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WeatherSimulator extends AbstractBehavior<EnvironmentMessages.EnvironmentCommand> {

    private EnvironmentMessages.WeatherCondition currentWeather;
    private final Random random = new Random();
    private final List<EnvironmentMessages.WeatherCondition> weatherConditions =
            Arrays.asList(EnvironmentMessages.WeatherCondition.values());

    private WeatherSimulator(ActorContext<EnvironmentMessages.EnvironmentCommand> context,
                             EnvironmentMessages.WeatherCondition initialWeather) {
        super(context);
        this.currentWeather = initialWeather;

        // Schedule periodic weather updates
        context.getSystem().scheduler().scheduleAtFixedRate(
                Duration.ofSeconds(10),
                Duration.ofSeconds(30),
                () -> {
                    int index = random.nextInt(weatherConditions.size());
                    context.getSelf().tell(new EnvironmentMessages.UpdateWeather(weatherConditions.get(index)));
                },
                context.getSystem().executionContext()
        );
    }

    public static Behavior<EnvironmentMessages.EnvironmentCommand> create(EnvironmentMessages.WeatherCondition initialWeather) {
        return Behaviors.setup(context -> new WeatherSimulator(context, initialWeather));
    }

    @Override
    public Receive<EnvironmentMessages.EnvironmentCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(EnvironmentMessages.GetWeather.class, this::onGetWeather)
                .onMessage(EnvironmentMessages.UpdateWeather.class, this::onUpdateWeather)
                .onMessage(EnvironmentMessages.SetWeather.class, this::onSetWeather)
                .build();
    }

    private Behavior<EnvironmentMessages.EnvironmentCommand> onGetWeather(EnvironmentMessages.GetWeather get) {
        get.replyTo.tell(new EnvironmentMessages.WeatherResponse(currentWeather));
        return this;
    }

    private Behavior<EnvironmentMessages.EnvironmentCommand> onUpdateWeather(EnvironmentMessages.UpdateWeather update) {
        currentWeather = update.change;
        getContext().getLog().info("Weather updated to: {}", currentWeather);
        return this;
    }

    private Behavior<EnvironmentMessages.EnvironmentCommand> onSetWeather(EnvironmentMessages.SetWeather set) {
        currentWeather = set.weather;
        getContext().getLog().info("Weather set to: {}", currentWeather);
        return this;
    }
}*/
