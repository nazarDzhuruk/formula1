package ua.com.foxminded.formula1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FormatStreamTest {
    @Test
    public void testPrettyFormat() throws IOException {
        FormatStream formatStream = new FormatStream();
        List<String> actual = formatStream.print(myRacersTest());
        List<String> expectedTable = Arrays.asList("1. Sebastian Vettel |FERRARI                    |1:4.415",
                "2. Daniel Ricciardo |RED BULL RACING TAG HEUER  |1:12.013",
                "3. Valtteri Bottas  |MERCEDES                   |1:12.434",
                "---------------------------------------------------------",
                "4. Lewis Hamilton   |MERCEDES                   |1:12.46",
                "5. Stoffel Vandoorne|MCLAREN RENAULT            |1:12.463",
                "6. Kimi Raikkonen   |FERRARI                    |1:12.639",
                "7. Fernando Alonso  |MCLAREN RENAULT            |1:12.657",
                "8. Sergey Sirotkin  |WILLIAMS MERCEDES          |1:12.706",
                "9. Charles Leclerc  |SAUBER FERRARI             |1:12.829",
                "10. Sergio Perez     |FORCE INDIA MERCEDES       |1:12.848",
                "11. Romain Grosjean  |HAAS FERRARI               |1:12.93",
                "12. Pierre Gasly     |SCUDERIA TORO ROSSO HONDA  |1:12.941",
                "13. Carlos Sainz     |RENAULT                    |1:12.95",
                "14. Esteban Ocon     |FORCE INDIA MERCEDES       |1:13.028",
                "15. Nico Hulkenberg  |RENAULT                    |1:13.065",
                "16. Brendon Hartley  |SCUDERIA TORO ROSSO HONDA  |1:13.179",
                "17. Marcus Ericsson  |SAUBER FERRARI             |1:13.265",
                "18. Lance Stroll     |WILLIAMS MERCEDES          |1:13.323",
                "19. Kevin Magnussen  |HAAS FERRARI               |1:13.393");
        assertThat(actual, equalTo(expectedTable));
    }

    public Racer myRacersTest() throws IOException {
        Stream<String> start = Files.lines(Paths.get("src/main/resources/race-data/start.log"));
        Stream<String> end = Files.lines(Paths.get("src/main/resources/race-data/end.log"));
        Stream<String> abbreviated = Files.lines(Paths.get("src/main/resources/race-data/abbreviations.txt"));
        StreamToList streamToList = new StreamToList();
        Racer racer = streamToList.list(start, end, abbreviated);
        return racer;
    }
}
