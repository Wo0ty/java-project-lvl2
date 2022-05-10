package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import static com.google.common.io.Files.getFileExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static Path getAbsolutePath(final String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static File getFile(String fileName) {
        return new File(getAbsolutePath(fileName).toString());
    }

    public static Map<String, Object> getMapFromFile(final String fileName) throws IOException {
        String ext = getFileExtension(fileName);
        ObjectMapper objectMapper;

        if (ext.equals("json")) {
            objectMapper = new ObjectMapper();
            return objectMapper.readValue(getFile(fileName), new TypeReference<TreeMap<String, Object>>() { });
        } else if (ext.equals("yaml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
            return objectMapper.readValue(getFile(fileName), new TypeReference<TreeMap<String, Object>>() { });
        }

        return null;
    }

}
