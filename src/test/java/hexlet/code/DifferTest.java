package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {

    private final String pathToResources = "src/test/resources/";

    @Test
    void compareJsonFormatTest() throws IOException {
        String fileJsonPath1 = pathToResources + "plain1.json";
        String fileJsonPath2 = pathToResources + "plain2.json";

        String expected = Files.readString(Path.of(pathToResources + "resultJson.txt"));
        String actual = Differ.generate(fileJsonPath1, fileJsonPath2, "stylish");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void compareYamlFormatTest() throws IOException {
        String fileYamlPath1 = pathToResources + "plain1.yaml";
        String fileYamlPath2 = pathToResources + "plain2.yaml";

        String expected = Files.readString(Path.of(pathToResources + "resultYaml.txt"));
        String actual = Differ.generate(fileYamlPath1, fileYamlPath2, "stylish");

        Assertions.assertEquals(expected, actual);
    }
}
