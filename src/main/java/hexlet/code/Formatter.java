package hexlet.code;

import hexlet.code.formatters.StylishFormatter;

public class Formatter {
    public static hexlet.code.formatters.Formatter chooseFormat(String formatterName) {
        return new StylishFormatter();
    }
}
