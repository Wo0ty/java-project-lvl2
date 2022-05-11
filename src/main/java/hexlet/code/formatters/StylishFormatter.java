package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.Map;

public final class StylishFormatter implements Formatter {

    @Override
    public String format(Map<String, Object> parsedMap1,
                         Map<String, Object> parsedMap2,
                         Map<String, Differ.Status> diffMap) {
        StringBuilder builder = new StringBuilder("{\n");

        for (Map.Entry<String, Differ.Status> record : diffMap.entrySet()) {
            String key = record.getKey();
            Differ.Status status = record.getValue();

            switch (status) {
                case UNCHANGED -> builder.append(createUnchangedRecordLine(key, parsedMap1.get(key)));
                case CHANGED -> builder.append(createChangedRecordLine(key, parsedMap1.get(key), parsedMap2.get(key)));
                case REMOVED -> builder.append(createRemovedRecordLine(key, parsedMap1.get(key)));
                default -> builder.append(createAddedRecordLine(key, parsedMap2.get(key)));
            }

        }

        return builder.append("}").toString();
    }

    private static StringBuilder createAddedRecordLine(final String key, final Object value) {
        return new StringBuilder().
                append("  + ").
                append(createRemainingPartRecord(key, value));
    }

    private static StringBuilder createRemovedRecordLine(final String key, final Object value) {
        return new StringBuilder().
                append("  - ").
                append(createRemainingPartRecord(key, value));
    }

    private static StringBuilder createUnchangedRecordLine(final String key, final Object value) {
        final int spaceCount = 4;
        return new StringBuilder().
                append(" ".repeat(spaceCount)).
                append(createRemainingPartRecord(key, value));
    }

    private static StringBuilder createChangedRecordLine(final String key, final Object value1, final Object value2) {
        return new StringBuilder().
                append(createRemovedRecordLine(key, value1)).
                append(createAddedRecordLine(key, value2));
    }

    private static StringBuilder createRemainingPartRecord(final String key, final Object value) {
        return new StringBuilder().
                append(key).
                append(": ").
                append(value).
                append("\n");
    }
}
