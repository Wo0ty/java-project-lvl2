package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.FormatterConstants.KEY;
import static hexlet.code.formatters.FormatterConstants.STATUS;
import static hexlet.code.formatters.FormatterConstants.OLD_VALUE;
import static hexlet.code.formatters.FormatterConstants.NEW_VALUE;
import static hexlet.code.formatters.FormatterConstants.ADDED;
import static hexlet.code.formatters.FormatterConstants.REMOVED;
import static hexlet.code.formatters.FormatterConstants.CHANGED;
import static hexlet.code.formatters.FormatterConstants.UNCHANGED;

public final class StylishFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> diffList) {
        StringBuilder builder = new StringBuilder("{\n");

        for (Map<String, Object> node : diffList) {
            Object key = node.get(KEY);
            String status = (String) node.get(STATUS);
            Object oldValue = node.get(OLD_VALUE);
            Object newValue = node.get(NEW_VALUE);

            switch (status) {
                case ADDED -> builder.append(generateNewRecord(key, newValue, '+'));
                case REMOVED -> builder.append(generateNewRecord(key, oldValue, '-'));
                case CHANGED -> {
                    builder.append(generateNewRecord(key, oldValue, '-'));
                    builder.append(generateNewRecord(key, newValue, '+'));
                }
                case UNCHANGED -> builder.append(generateNewRecord(key, oldValue, ' '));
                default -> { }
            }
        }

        return builder.append("}").toString();
    }

    public static String generateNewRecord(final Object key, final Object value, final char sign) {
        return new StringBuilder()
                .append("  ")
                .append(sign)
                .append(" ")
                .append(key)
                .append(": ")
                .append(value)
                .append("\n").
                toString();

    }
}
