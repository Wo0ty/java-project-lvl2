package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static hexlet.code.formatters.FormatterConstants.KEY;
import static hexlet.code.formatters.FormatterConstants.STATUS;
import static hexlet.code.formatters.FormatterConstants.OLD_VALUE;
import static hexlet.code.formatters.FormatterConstants.NEW_VALUE;
import static hexlet.code.formatters.FormatterConstants.ADDED;
import static hexlet.code.formatters.FormatterConstants.REMOVED;
import static hexlet.code.formatters.FormatterConstants.CHANGED;

public class JsonFormatter implements Formatter{
    @Override
    public String format(List<Map<String, Object>> diffList) {
        ObjectMapper objectMapper = new ObjectMapper();
        //diffList.se
        try {
            return objectMapper.writeValueAsString(diffList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
