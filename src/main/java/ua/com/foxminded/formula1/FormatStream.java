package ua.com.foxminded.formula1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FormatStream {
    private static final String DASH = "-";
    private static final String VLINE = "|";
    private static final String SPACE = " ";
    private static final String REGEX = "[PTS]";
    private static final String ALLM = "M";
    private static final String DOTS = ":";
    private static final String DOT = ".";
    private static final String EMPTY_STRING = "";

    public List<String> print(Racer racer) {
        List<String> racers = racers(racer);
        int dashQuantity = racers.get(1).length();
        String addDashes = adjustableString(DASH, dashQuantity);
        racers.add(3, addDashes);
        return racers;
    }

    private List<String> racers(Racer racer) {
        List<String> names = formatNames(racer);
        List<String> cars = formatCars(racer);
        List<String> time = formatTime(racer);
        return IntStream.range(0, names.size())
                .mapToObj(i -> (i + 1) + DOT + SPACE + names.get(i) + cars.get(i) + SPACE + time.get(i))
                .collect(Collectors.toList());
    }

    private List<String> formatTime(Racer racer) {
        return racer.getRacer().stream()
                .map(racerList -> VLINE + racerList.getTime())
                .map(time -> time.replaceAll(REGEX, EMPTY_STRING))
                .map(time -> time.replace(ALLM, DOTS))
                .collect(Collectors.toList());
    }

    private List<String> formatCars(Racer racer) {
        List<String> carFormat = cars(racer);
        int maxLen = carFormat.stream().mapToInt(String::length).max().getAsInt();
        return carFormat.stream()
                .map(car -> String.format("%-" + maxLen + "s", car))
                .collect(Collectors.toList());
    }

    private List<String> formatNames(Racer racer) {
        List<String> surnames = names(racer);
        int maxLen = surnames.stream().mapToInt(String::length).max().getAsInt();
        return surnames.stream()
                .map(surname -> String.format("%-" + maxLen + "s", surname))
                .collect(Collectors.toList());
    }

    private List<String> names(Racer racer) {
        return racer.getRacer().stream()
                .map(RacerConstructor::getNames)
                .collect(Collectors.toList());
    }

    private List<String> cars(Racer racer) {
        return racer.getRacer().stream()
                .map(racerList -> VLINE + racerList.getCars() + SPACE)
                .collect(Collectors.toList());
    }

    private String adjustableString(String format, int number) {
        return IntStream.range(0, number).mapToObj(i -> format).collect(Collectors.joining());
    }
}
