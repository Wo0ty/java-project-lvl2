package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;;

public class Differ {
    public static String generate(final String filePath1, final String filePath2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String data1 = Files.readString(Paths.get(filePath1));
        Map<String, Object> parsedData1 = mapper.readValue(data1, new TypeReference<TreeMap<String, Object>>() {});

        String data2 = Files.readString(Paths.get(filePath2));
        Map<String, Object> parsedData2 = mapper.readValue(data2, new TypeReference<TreeMap<String, Object>>() {});

        StringBuilder stringBuilder = new StringBuilder("{\n");

        for (String key: parsedData1.keySet()) {
            Object value1 = parsedData1.get(key);
            if (parsedData2.containsKey(key)) {
                Object value2 = parsedData2.get(key);
                if (value1.equals(value2)) {
                    stringBuilder.append(generateLine(key, value1, ' '));
                } else {
                    stringBuilder.append(generateLine(key, value1, '-'));
                    stringBuilder.append(generateLine(key, value2, '+'));
                }
            } else {
                stringBuilder.append(generateLine(key, value1, '-'));
            }
        }

        for (String key: parsedData2.keySet()) {
            if (!parsedData1.containsKey(key)) {
                stringBuilder.append(generateLine(key, parsedData2.get(key), '+'));
            }
        }

        return stringBuilder.append("\n}").toString();
    }

    public static String generateLine(final String key, final Object value, char sign) {
        return new StringBuilder().
                append("  ").
                append(sign).
                append(" ").
                append(key).
                append(": ").
                append(value).
                append("\n").
                toString();
    }
}
