package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

import static com.google.common.io.Files.getFileExtension;


public class Differ {

    public static String generate(final String filePath1, final String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(final String filePath1,
                                  final String filePath2,
                                  String formatName) throws IOException {
        String fileExtension1 = getFileExtension(filePath1);
        String fileExtension2 = getFileExtension(filePath2);

        String fileContent1 = readFileFromPath(filePath1);
        String fileContent2 = readFileFromPath(filePath2);

        Map<String, Object> parsedMap1 = Parser.getMapFromFile(fileContent1, fileExtension1);
        Map<String, Object> parsedMap2 = Parser.getMapFromFile(fileContent2, fileExtension2);

        List<Map<String, Object>> differenceList = DiffBuilder.generateDiff(parsedMap1, parsedMap2);

        return Formatter.getFormatFor(formatName).format(differenceList);
    }

    public static Path getAbsolutePath(final String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static String readFileFromPath(String filePath) throws IOException {
        try {
            return Files.readString(getAbsolutePath(filePath));
        } catch (IOException e) {
            throw new IOException("File \"" + filePath + "\" not found. Check the file with path name and try again.");
        }
    }

}
