import java.util.HashMap;
import java.util.Map;

public class SimpleMessageService implements IMessageService {

    private final Map<String, String> responseLog;

    public SimpleMessageService() {
        this.responseLog = new HashMap<>();
        this.responseLog.put("default", "Welcome to the Simple Servlet System!");
    }

    @Override
    public String processRequest(String input) {
        if (input == null || input.trim().isEmpty()) {
            return responseLog.get("default");
        }
        return "Processed: " + input.toUpperCase();
    }
}