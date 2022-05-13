package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {

    private final String pathToResources = "src/test/resources/";
    private final String fileJsonPath1 = pathToResources + "plain1.json";
    private final String fileJsonPath2 = pathToResources + "plain2.json";
    private final String fileYamlPath1 = pathToResources + "plain1.yaml";
    private final String fileYamlPath2 = pathToResources + "plain2.yaml";
    private final String emptyJson = pathToResources + "emptyFile.json";

    private final String expectedStylish = pathToResources + "resultStylish.txt";

    @Test
    void generate_Stylish_Diff_With_Relative_Path_Json() throws IOException {
        String expected = Files.readString(Path.of(expectedStylish));
        String actual = Differ.generate(fileJsonPath1, fileJsonPath2, "stylish");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generate_Stylish_Diff_With_Absolute_Path_Json() throws IOException {
        String expected = Files.readString(Path.of(expectedStylish).toAbsolutePath());
        String actual = Differ.generate(fileJsonPath1, fileJsonPath2, "stylish");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generate_Stylish_Diff_With_Relative_Path_YAML() throws IOException {
        String expected = Files.readString(Path.of(expectedStylish));
        String actual = Differ.generate(fileYamlPath1, fileYamlPath2, "stylish");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generate_Stylish_Diff_With_Absolute_Path_YAML() throws IOException {
        String expected = Files.readString(Path.of(expectedStylish).toAbsolutePath());
        String actual = Differ.generate(fileYamlPath1, fileYamlPath2, "stylish");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generate_Stylish_Diff_With_Relative_Path_JSON_YAML() throws IOException {
        String expected = Files.readString(Path.of(expectedStylish));
        String actual = Differ.generate(fileJsonPath1, fileYamlPath2, "stylish");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void generate_Stylish_Diff_With_Absolute_Path_JSON_YAML() throws IOException {
        String expected = Files.readString(Path.of(expectedStylish).toAbsolutePath());
        String actual = Differ.generate(fileJsonPath1, fileYamlPath2, "stylish");

        Assertions.assertEquals(expected, actual);
    }
}
