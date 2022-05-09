package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {

    private final String pathToResourses = "src/test/resources/";

    private final String firstPlainFilePath = pathToResourses + "plain1.json";
    private final String secondPlainFilePath = pathToResourses + "plain2.json";
    private final String resultPlainFilePath = pathToResourses + "resultPlain";

    @Test
    void generateTest() throws IOException {
        String expected = Files.readString(Path.of(resultPlainFilePath));

        String actual = Differ.generate(firstPlainFilePath, secondPlainFilePath);

        Assertions.assertEquals(expected, actual);
    }
}
