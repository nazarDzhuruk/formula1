package ua.com.foxminded.formula1;

import java.time.Duration;

public class RacerConstructor {
    private String names;
    private String cars;
    private Duration time;

    public RacerConstructor(String names, String cars, Duration time) {
        this.names = names;
        this.cars = cars;
        this.time = time;
    }
    public Duration getTime() {
        return time;
    }
    public String getNames() {
        return names;
    }
    public String getCars() {
        return cars;
    }
}
