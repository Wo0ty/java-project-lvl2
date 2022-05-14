package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.List;

public class Differ {

    public static String generate(final String filePath1, final String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(final String filePath1,
                                  final String filePath2,
                                  String formatName) throws IOException {
        Map<String, Object> parsedMap1 = Parser.getMapFromFile(filePath1);
        Map<String, Object> parsedMap2 = Parser.getMapFromFile(filePath2);

        List<Map<String, Object>> differenceList = Analyser.generateDiff(parsedMap1, parsedMap2);

        return Formatter.setFormat(formatName).format(differenceList);
    }
}
