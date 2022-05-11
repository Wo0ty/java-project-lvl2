package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.Map;

public interface Formatter {
    String format(Map<String, Object> parsedMap1,
                  Map<String, Object> parsedMap2,
                  Map<String, Differ.Status> diffMap);
}
