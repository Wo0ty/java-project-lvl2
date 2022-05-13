package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;

public class Formatter {
    public static hexlet.code.formatters.Formatter setFormat(String formatName) throws IOException {
        return switch (formatName) {
            case "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            default -> throw new IOException("Format \""
                    + formatName
                    + "\" is not available. Available formats: stylish, plain");
        };
    }
}
