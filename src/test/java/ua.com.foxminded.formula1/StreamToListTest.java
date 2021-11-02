package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamToListTest {
    @Test
    public void containsTimeTest() throws IOException {
        List<String> actualTime = myRacersTest().stream().map(RacerConstructor::getTime)
                .map(String::valueOf)
                .collect(Collectors.toList());
        List<String> expected = Arrays.asList("PT1M4.415S",
                "PT1M12.013S",
                "PT1M12.434S",
                "PT1M12.46S",
                "PT1M12.463S",
                "PT1M12.639S",
                "PT1M12.657S",
                "PT1M12.706S",
                "PT1M12.829S",
                "PT1M12.848S",
                "PT1M12.93S",
                "PT1M12.941S",
                "PT1M12.95S",
                "PT1M13.028S",
                "PT1M13.065S",
                "PT1M13.179S",
                "PT1M13.265S",
                "PT1M13.323S",
                "PT1M13.393S");
        assertThat(actualTime, equalTo(expected));
    }

    @Test
    public void containsCarsTest() throws IOException {
        List<String> actualCars = myRacersTest().stream().map(RacerConstructor::getCars).collect(Collectors.toList());
        List<String> expected = Arrays.asList("FERRARI",
                "RED BULL RACING TAG HEUER",
                "MERCEDES",
                "MERCEDES",
                "MCLAREN RENAULT",
                "FERRARI",
                "MCLAREN RENAULT",
                "WILLIAMS MERCEDES",
                "SAUBER FERRARI",
                "FORCE INDIA MERCEDES",
                "HAAS FERRARI",
                "SCUDERIA TORO ROSSO HONDA",
                "RENAULT",
                "FORCE INDIA MERCEDES",
                "RENAULT",
                "SCUDERIA TORO ROSSO HONDA",
                "SAUBER FERRARI",
                "WILLIAMS MERCEDES",
                "HAAS FERRARI");
        assertThat(actualCars, equalTo(expected));
    }

    @Test
    public void containsNamesTest() throws IOException {
        List<String> actualNames = myRacersTest().stream().map(RacerConstructor::getNames).collect(Collectors.toList());
        List<String> expected = Arrays.asList("Sebastian Vettel",
                "Daniel Ricciardo",
                "Valtteri Bottas",
                "Lewis Hamilton",
                "Stoffel Vandoorne",
                "Kimi Raikkonen",
                "Fernando Alonso",
                "Sergey Sirotkin",
                "Charles Leclerc",
                "Sergio Perez",
                "Romain Grosjean",
                "Pierre Gasly",
                "Carlos Sainz",
                "Esteban Ocon",
                "Nico Hulkenberg",
                "Brendon Hartley",
                "Marcus Ericsson",
                "Lance Stroll",
                "Kevin Magnussen");
        assertThat(actualNames, equalTo(expected));
    }

    public List<RacerConstructor> myRacersTest() throws IOException {
        Stream<String> start = Files.lines(Paths.get("src/main/resources/race-data/start.log"));
        Stream<String> end = Files.lines(Paths.get("src/main/resources/race-data/end.log"));
        Stream<String> abbreviated = Files.lines(Paths.get("src/main/resources/race-data/abbreviations.txt"));
        StreamToList streamToList = new StreamToList();
        Racer racer = streamToList.list(start, end, abbreviated);
        return racer.getRacer();
    }
}
