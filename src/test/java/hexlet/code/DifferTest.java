package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {
    private static String expectedStylishContent;
    private static String expectedPlainContent;
    private static String expectedStylishSameContent;

    private static final String PATH_TO_RESOURCES = "src/test/resources/";
    private final String emptyJson = PATH_TO_RESOURCES + "emptyFile.json";

    private final String jsonPath1 = PATH_TO_RESOURCES + "file1.json";
    private final String jsonPath2 = PATH_TO_RESOURCES + "file2.json";
    private final String yamlPath1 = PATH_TO_RESOURCES + "file1.yaml";
    private final String yamlPath2 = PATH_TO_RESOURCES + "file2.yaml";

    private final String jsonAbsolutePath1 = new File(jsonPath1).getAbsolutePath();
    private final String jsonAbsolutePath2 = new File(jsonPath2).getAbsolutePath();
    private final String yamlAbsolutePath1 = new File(yamlPath1).getAbsolutePath();
    private final String yamlAbsolutePath2 = new File(yamlPath2).getAbsolutePath();

    @BeforeAll
    static void readFiles() throws IOException {
        expectedStylishContent = Files.readString(Path.of(PATH_TO_RESOURCES + "expectedStylish.txt").toAbsolutePath());
        expectedPlainContent = Files.readString(Path.of(PATH_TO_RESOURCES + "expectedPlain.txt").toAbsolutePath());
        expectedStylishSameContent = Files.readString(Path.of(PATH_TO_RESOURCES + "expectedStylishSameFile.txt").toAbsolutePath());
    }
    @Test
    void generateStylishDiffWithRelativePathJSON() throws IOException {
        String actual = Differ.generate(jsonPath1, jsonPath2);
        Assertions.assertEquals(expectedStylishContent, actual);
    }

    @Test
    void generateStylishDiffWithAbsolutePathJSON() throws IOException {
        String actual = Differ.generate(jsonAbsolutePath1, jsonAbsolutePath2, "stylish");
        Assertions.assertEquals(expectedStylishContent, actual);
    }

    @Test
    void generatePlainDiffWithRelativePathJSON() throws IOException {
        String actual = Differ.generate(jsonPath1, jsonPath2, "plain");
        Assertions.assertEquals(expectedPlainContent, actual);
    }

    @Test
    void generatePlainDiffWithAbsolutePathJSON() throws IOException {
        String actual = Differ.generate(jsonAbsolutePath1, jsonAbsolutePath2, "plain");
        Assertions.assertEquals(expectedPlainContent, actual);
    }

    @Test
    void generateStylishDiffWithRelativePathYAML() throws IOException {
        String actual = Differ.generate(yamlPath1, yamlPath2, "stylish");
        Assertions.assertEquals(expectedStylishContent, actual);
    }

    @Test
    void generateStylishDiffWithAbsolutePathYAML() throws IOException {
        String actual = Differ.generate(yamlAbsolutePath1, yamlAbsolutePath2, "stylish");
        Assertions.assertEquals(expectedStylishContent, actual);
    }

    @Test
    void generatePlainDiffWithRelativePathYAML() throws IOException {
        String actual = Differ.generate(yamlPath1, yamlPath2, "plain");
        Assertions.assertEquals(expectedPlainContent, actual);
    }

    @Test
    void generatePlainDiffWithAbsolutePathYAML() throws IOException {
        String actual = Differ.generate(yamlAbsolutePath1, yamlAbsolutePath2, "plain");
        Assertions.assertEquals(expectedPlainContent, actual);
    }

    @Test
    void generateStylishDiffWithRelativePathJSONYAML() throws IOException {
        String actual = Differ.generate(jsonPath1, yamlPath2, "stylish");
        Assertions.assertEquals(expectedStylishContent, actual);
    }

    @Test
    void generateStylishDiffWithAbsolutePathJSONYAML() throws IOException {
        String actual = Differ.generate(jsonAbsolutePath1, yamlAbsolutePath2, "stylish");
        Assertions.assertEquals(expectedStylishContent, actual);
    }

    @Test
    void generatePlainDiffWithRelativePathJSONYAML() throws IOException {
        String actual = Differ.generate(jsonPath1, yamlPath2, "plain");
        Assertions.assertEquals(expectedPlainContent, actual);
    }

    @Test
    void generatePlainDiffWithAbsolutePathJSONYAML() throws IOException {
        String actual = Differ.generate(jsonAbsolutePath1, yamlAbsolutePath2, "plain");
        Assertions.assertEquals(expectedPlainContent, actual);
    }

    @Test
    void generateStylishDiffSameFiles() throws IOException {
        String actual = Differ.generate(jsonAbsolutePath1, jsonAbsolutePath1, "stylish");
        Assertions.assertEquals(expectedStylishSameContent, actual);
    }

    @Test
    void generateJsonDiffWithAbsolutePathJSON() throws IOException {
        String actual = Differ.generate(jsonAbsolutePath1, jsonAbsolutePath2, "json");
        String expectedJsonContent = Files.readString(Path.of(PATH_TO_RESOURCES +
                "expectedStylishSameFile.txt").
                toAbsolutePath());
        Assertions.assertEquals(expectedJsonContent, actual);
    }

    @Test
    void generatePlainDiffWithRelativePathWrongFormatName() {
        Exception exception = Assertions.assertThrows(IOException.class,
                () -> Differ.generate(jsonPath1, jsonPath2, "txt"));

        String expectedMessage = "Format \"txt\" is not available. Available formats: stylish, plain, json.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void generatePlainDiffMissingFirstFile() {
        String missingFile = "MissingFile.json";
        Exception exception = Assertions.assertThrows(IOException.class,
                () -> Differ.generate(missingFile, jsonPath2));

        String expectedMessage = "File \"" + missingFile + "\" not found. Check the file with path name and try again.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void generatePlainDiffMissingSecondFile() {
        String missingFile = "MissingFile.json";
        Exception exception = Assertions.assertThrows(IOException.class,
                () -> Differ.generate(jsonPath1, missingFile));

        String expectedMessage = "File \"" + missingFile + "\" not found. Check the file with path name and try again.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void generatePlainDiffBetweenEmptyFiles() throws IOException {
        String actual = Differ.generate(emptyJson, emptyJson, "stylish");
        String expected = "{\n}";
        Assertions.assertEquals(expected, actual);
    }
}
