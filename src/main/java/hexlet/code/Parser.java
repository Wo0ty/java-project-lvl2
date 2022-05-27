package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public final class Parser {

    private static final String JSON = "json";
    private static final String YML = "yml";
    private static final String YAML = "yaml";

    public static Map<String, Object> getMapFromFile(final String content, final String format) throws IOException {
        try {
            ObjectMapper objectMapper = switch(format.toLowerCase()) {
                case JSON -> new ObjectMapper();
                case YML, YAML -> new ObjectMapper(new YAMLFactory());
                default -> throw new IllegalStateException("Unexpected file extension: " + format);
            };
            return objectMapper.readValue(content, new TypeReference<TreeMap<String, Object>>() { });
        } catch (JsonProcessingException e) {
            throw new JsonProcessingException("Error reading data from files.") { };
        }
    }

}
