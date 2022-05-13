package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Objects;

import static hexlet.code.formatters.FormatterConstants.KEY;
import static hexlet.code.formatters.FormatterConstants.STATUS;
import static hexlet.code.formatters.FormatterConstants.OLD_VALUE;
import static hexlet.code.formatters.FormatterConstants.NEW_VALUE;
import static hexlet.code.formatters.FormatterConstants.ADDED;
import static hexlet.code.formatters.FormatterConstants.REMOVED;
import static hexlet.code.formatters.FormatterConstants.CHANGED;
import static hexlet.code.formatters.FormatterConstants.UNCHANGED;

public class Analyser {
    public static List<Map<String, Object>> generateDiff(final Map<String, Object> firstMap,
                                                              final Map<String, Object> secondMap) {
        Set<String> keys = getKeySetUnion(firstMap, secondMap);

        List<Map<String, Object>> diffList = new LinkedList<>();

        for (String key: keys) {
            Map<String, Object> diffMap = new LinkedHashMap<>();
            Object firstValue = firstMap.get(key);
            Object secondValue = secondMap.get(key);

            if (firstMap.containsKey(key)) {
                if (secondMap.containsKey(key)) {
                    if (Objects.equals(firstValue, secondValue)) {
                        diffList.add(generateDiffRecord(key, UNCHANGED, firstValue, secondValue));
                    } else {
                        diffList.add(generateDiffRecord(key, CHANGED, firstValue, secondValue));
                    }
                } else {
                    diffList.add(generateDiffRecord(key, REMOVED, firstValue, secondValue));
                }
            } else {
                diffList.add(generateDiffRecord(key, ADDED, firstValue, secondValue));
            }
        }

        return diffList;
    }

    private static Map<String, Object> generateDiffRecord(final Object key, final Object status,
                                                          final Object oldValue, final Object newValue) {
        Map<String, Object> diffMap = new LinkedHashMap<>();
        diffMap.put(KEY, key);
        diffMap.put(STATUS, status);
        diffMap.put(OLD_VALUE, oldValue);
        diffMap.put(NEW_VALUE, newValue);

        return diffMap;
    }

    private static Set<String> getKeySetUnion(final Map<String, Object> firstMap,
                                              final Map<String, Object> secondMap) {
        Set<String> keys = new TreeSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());
        return keys;
    }
}
