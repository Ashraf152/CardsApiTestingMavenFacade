package Logic;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ApiResponse parseJson(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, ApiResponse.class);
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception based on your needs
            return null;
        }
    }
}
