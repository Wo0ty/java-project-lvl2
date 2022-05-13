package hexlet.code.formatters;

import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import static hexlet.code.formatters.FormatterConstants.KEY;
import static hexlet.code.formatters.FormatterConstants.STATUS;
import static hexlet.code.formatters.FormatterConstants.OLD_VALUE;
import static hexlet.code.formatters.FormatterConstants.NEW_VALUE;
import static hexlet.code.formatters.FormatterConstants.ADDED;
import static hexlet.code.formatters.FormatterConstants.REMOVED;
import static hexlet.code.formatters.FormatterConstants.CHANGED;

public final class PlainFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> diffList) {
        StringBuilder builder = new StringBuilder();

        for (Map<String, Object> node : diffList) {
            Object key = node.get(KEY);
            String status = (String) node.get(STATUS);
            Object oldValue = node.get(OLD_VALUE);
            Object newValue = node.get(NEW_VALUE);

            switch (status) {
                case ADDED -> {
                    builder.append(String.format("Property '%s' was added with value: %s\n",
                            key,
                            prepareValueForOutput(newValue)));
                }
                case REMOVED -> {
                    builder.append(String.format("Property '%s' was removed\n", key));
                }
                case CHANGED -> {
                    builder.append(String.format("Property '%s' was updated. From %s to %s\n",
                            key,
                            prepareValueForOutput(oldValue),
                            prepareValueForOutput(newValue)));
                }
                default -> { }
            }
        }
        return builder.toString().trim();
    }

    private String prepareValueForOutput(Object obj) {
        if (obj instanceof Object[] || obj instanceof Collections || obj instanceof Map
                || obj instanceof ArrayList<?>) {
            return "[complex value]";
        } else if (obj instanceof String) {
            return String.format("'%s'", obj);
        } else if (obj == null) {
            return null;
        }
        return obj.toString();
    }
}
