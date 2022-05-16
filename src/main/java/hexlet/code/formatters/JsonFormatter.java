package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonFormatter implements Formatter {
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
