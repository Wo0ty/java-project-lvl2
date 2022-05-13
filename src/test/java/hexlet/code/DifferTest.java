package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static final String PATH_TO_RESOURCES = "src/test/resources/";
    private final String emptyJson = PATH_TO_RESOURCES + "emptyFile.json";

    private final String fileJsonPath1 = PATH_TO_RESOURCES + "plain1.json";
    private final String fileJsonPath2 = PATH_TO_RESOURCES + "plain2.json";
    private final String fileYamlPath1 = PATH_TO_RESOURCES + "plain1.yaml";
    private final String fileYamlPath2 = PATH_TO_RESOURCES + "plain2.yaml";

    private final String fileJsonAbsolutePath1 = new File(fileJsonPath1).getAbsolutePath();
    private final String fileJsonAbsolutePath2 = new File(fileJsonPath2).getAbsolutePath();
    private final String fileYAMLAbsolutePath1 = new File(fileYamlPath1).getAbsolutePath();
    private final String fileYAMLAbsolutePath2 = new File(fileYamlPath2).getAbsolutePath();

    @BeforeAll
    static void readFiles() throws IOException {
        expectedStylish = Files.readString(Path.of(PATH_TO_RESOURCES + "resultStylish.txt").toAbsolutePath());
        expectedPlain = Files.readString(Path.of(PATH_TO_RESOURCES + "resultPlain.txt").toAbsolutePath());
    }
    @Test
    void generateStylishDiffWithRelativePathJSON() throws IOException {
        String actual = Differ.generate(fileJsonPath1, fileJsonPath2, "stylish");
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    void generateStylishDiffWithAbsolutePathJSON() throws IOException {
        String actual = Differ.generate(fileJsonAbsolutePath1, fileJsonAbsolutePath2, "stylish");
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    void generatePlainDiffWithRelativePathJSON() throws IOException {
        String actual = Differ.generate(fileJsonPath1, fileJsonPath2, "plain");
        Assertions.assertEquals(expectedPlain, actual);
    }

    @Test
    void generatePlainDiffWithAbsolutePathJSON() throws IOException {
        String actual = Differ.generate(fileJsonAbsolutePath1, fileJsonAbsolutePath2, "plain");
        Assertions.assertEquals(expectedPlain, actual);
    }

    @Test
    void generateStylishDiffWithRelativePathYAML() throws IOException {
        String actual = Differ.generate(fileYamlPath1, fileYamlPath2, "stylish");
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    void generateStylishDiffWithAbsolutePathYAML() throws IOException {
        String actual = Differ.generate(fileYAMLAbsolutePath1, fileYAMLAbsolutePath2, "stylish");
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    void generatePlainDiffWithRelativePathYAML() throws IOException {
        String actual = Differ.generate(fileYamlPath1, fileYamlPath2, "plain");
        Assertions.assertEquals(expectedPlain, actual);
    }

    @Test
    void generatePlainDiffWithAbsolutePathYAML() throws IOException {
        String actual = Differ.generate(fileYAMLAbsolutePath1, fileYAMLAbsolutePath2, "plain");
        Assertions.assertEquals(expectedPlain, actual);
    }

    @Test
    void generateStylishDiffWithRelativePathJSONYAML() throws IOException {
        String actual = Differ.generate(fileJsonPath1, fileYamlPath2, "stylish");
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    void generateStylishDiffWithAbsolutePathJSONYAML() throws IOException {
        String actual = Differ.generate(fileJsonAbsolutePath1, fileYAMLAbsolutePath2, "stylish");
        Assertions.assertEquals(expectedStylish, actual);
    }

    @Test
    void generatePlainDiffWithRelativePathJSONYAML() throws IOException {
        String actual = Differ.generate(fileJsonPath1, fileYamlPath2, "plain");
        Assertions.assertEquals(expectedPlain, actual);
    }

    @Test
    void generatePlainDiffWithAbsolutePathJSONYAML() throws IOException {
        String actual = Differ.generate(fileJsonAbsolutePath1, fileYAMLAbsolutePath2, "plain");
        Assertions.assertEquals(expectedPlain, actual);
    }

    @Test
    void generatePlainDiffWithRelativePathWrongFormatName() {
        Exception exception = Assertions.assertThrows(IOException.class,
                () -> Differ.generate(fileJsonPath1, fileJsonPath2, "txt"));

        String expectedMessage = "Format \"txt\" is not available. Available formats: stylish, plain";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
