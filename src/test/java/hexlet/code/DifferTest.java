package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {

    private final String pathToResourses = "src/test/resources/";

    @Test
    void compareJsonFormatTest() throws IOException {
        String fileJsonPath1 = pathToResourses + "plain1.json";
        String fileJsonPath2 = pathToResourses + "plain2.json";

        String expected = Files.readString(Path.of(pathToResourses + "resultJson.txt"));
        String actual = Differ.generate(fileJsonPath1, fileJsonPath2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void compareYamlFormatTest() throws IOException {
        String fileYamlPath1 = pathToResourses + "plain1.yaml";
        String fileYamlPath2 = pathToResourses + "plain2.yaml";

        String expected = Files.readString(Path.of(pathToResourses + "resultYaml.txt"));
        String actual = Differ.generate(fileYamlPath1, fileYamlPath2);

        Assertions.assertEquals(expected, actual);
    }
}
