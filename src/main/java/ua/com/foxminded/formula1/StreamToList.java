package ua.com.foxminded.formula1;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToList {

    private static final String UNDERLINE = "_";
    private static final String DATA = "2018-05-24";
    private static final String EMPTY_STRING = "";
    private Racer racer;

    public StreamToList() {
        this.racer = new Racer();
    }

    public Racer list(Stream<String> start, Stream<String> end, Stream<String> abbreviations) {
        racer.setRacer(racersTable(start, end, abbreviations));
        return racer;
    }

    private Map<String, LocalTime> abbreviationTime(Stream<String> start) {
        return start.sorted(Comparator.naturalOrder())
                .map(part -> part.replace(DATA, EMPTY_STRING))
                .map(parts -> parts.split(UNDERLINE))
                .collect(Collectors.toMap(abb -> abb[0], time -> LocalTime.parse(time[1])));
    }

    private List<RacerConstructor> racersTable(Stream<String> start, Stream<String> end, Stream<String> abbreviation) {
        Map<String, LocalTime> startTime = abbreviationTime(start);
        Map<String, LocalTime> endTime = abbreviationTime(end);
        return abbreviation
                .sorted(Comparator.naturalOrder())
                .map(lines -> lines.split(UNDERLINE))
                .map(line -> new RacerConstructor(line[1], line[2], Duration.between(startTime.get(line[0]), endTime.get(line[0]))))
                .sorted(Comparator.comparing(RacerConstructor::getTime))
                .collect(Collectors.toList());
    }
}
