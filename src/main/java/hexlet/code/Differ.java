package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(final String filePath1, final String filePath2) throws IOException {
        Map<String, Object> parsedMap1 = Parser.getMapFromFile(filePath1);
        Map<String, Object> parsedMap2 = Parser.getMapFromFile(filePath2);
        Set<String> keySet = getKeySetUnion(parsedMap1, parsedMap2);

        return findDiffBetweenTwoMap(parsedMap1, parsedMap2, keySet);
    }

    private static String findDiffBetweenTwoMap(Map<String, Object> map1,
                                                Map<String, Object> map2,
                                                Set<String> keyUnion) {
        StringBuilder stringBuilder = new StringBuilder("{\n");


        for (String key: keyUnion) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            if (value1 != null && value2 != null) {
                if (value1.equals(value2)) {
                    stringBuilder.append(generateLine(key, value1, ' '));
                } else {
                    stringBuilder.append(generateLine(key, value1, '-'));
                    stringBuilder.append(generateLine(key, value2, '+'));
                }
            } else if (value1 != null && value2 == null) {
                stringBuilder.append(generateLine(key, value1, '-'));
            } else {
                stringBuilder.append(generateLine(key, map2.get(key), '+'));
            }
        }

        return stringBuilder.append("}").toString();
    }

    private static Set<String> getKeySetUnion(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keySetUnion = new TreeSet<>();

        keySetUnion.addAll(map1.keySet());
        keySetUnion.addAll(map2.keySet());

        return keySetUnion;
    }

    private static String generateLine(final String key, final Object value, char sign) {
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
