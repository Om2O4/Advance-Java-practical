import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        IMessageService testService = new SimpleMessageService();
        String actual = testService.processRequest("test_run");
        String expected = "Processed: TEST_RUN";

        response.setContentType("text/plain");
        response.getWriter().write(actual.equals(expected) ? "PASS" : "FAIL");
    }
}