package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import static com.google.common.io.Files.getFileExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static Path getAbsolutePath(final String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static Map<String, Object> getMapFromFile(final String fileName) throws IOException {
        String ext = getFileExtension(fileName);
        try {
            String file = Files.readString(getAbsolutePath(fileName));

            if (ext.equals("json")) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(file, new TypeReference<TreeMap<String, Object>>() { });
            } else if (ext.equals("yaml")) {
                ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                return objectMapper.readValue(file, new TypeReference<TreeMap<String, Object>>() { });
            }
        } catch (IOException e) {
            throw new IOException("File \"" + fileName + "\" not found. Check the file with path name and try again.");
        }

        return null;
    }

}
