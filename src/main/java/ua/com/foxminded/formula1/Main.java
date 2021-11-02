package ua.com.foxminded.formula1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Stream<String> start = Files.lines(Paths.get("src/main/resources/race-data/start.log"));
        Stream<String> end = Files.lines(Paths.get("src/main/resources/race-data/end.log"));
        Stream<String> abbreviated = Files.lines(Paths.get("src/main/resources/race-data/abbreviations.txt"));

        StreamToList streamToList = new StreamToList();
        Racer racer = streamToList.list(start, end, abbreviated);
        FormatStream formatStream = new FormatStream();
        formatStream.print(racer).forEach(System.out::println);
    }
}
