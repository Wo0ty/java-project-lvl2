package hexlet.code;
import java.io.IOException;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {

    public enum Status {
        ADDED, REMOVED, CHANGED, UNCHANGED
    }

    public static String generate(final String filePath1,
                                  final String filePath2,
                                  String formatName) throws IOException {
        Map<String, Object> parsedMap1 = Parser.getMapFromFile(filePath1);
        Map<String, Object> parsedMap2 = Parser.getMapFromFile(filePath2);
        Set<String> keySet = getKeySetUnion(parsedMap1, parsedMap2);

        Map<String, Status> diffMap = createDiffMap(keySet, parsedMap1, parsedMap2);

        return Formatter.chooseFormat(formatName).format(parsedMap1, parsedMap2, diffMap);
    }

    private static Map<String, Status> createDiffMap(final Set<String> keySet,
                                                     final Map<String, Object> parsedMap1,
                                                     final Map<String, Object> parsedMap2) {
        Map<String, Status> diffMap = new TreeMap<String, Status>();

        for (String key: keySet) {
            if (parsedMap1.containsKey(key)) {
                if (parsedMap2.containsKey(key)) {
                    if (Objects.equals(parsedMap1.get(key), parsedMap2.get(key))) {
                        diffMap.put(key, Status.UNCHANGED);
                    } else {
                        diffMap.put(key, Status.CHANGED);
                    }
                } else {
                    diffMap.put(key, Status.REMOVED);
                }
            } else {
                diffMap.put(key, Status.ADDED);
            }
        }

        return diffMap;
    }

    private static Set<String> getKeySetUnion(final Map<String, Object> map1, final Map<String, Object> map2) {
        Set<String> keySetUnion = new TreeSet<>(map1.keySet());
        keySetUnion.addAll(map2.keySet());

        return keySetUnion;
    }
}
