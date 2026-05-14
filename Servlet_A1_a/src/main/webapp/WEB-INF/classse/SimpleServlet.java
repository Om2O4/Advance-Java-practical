import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet extends HttpServlet {

    private final IMessageService messageService;

    public SimpleServlet() {
        this.messageService = new SimpleMessageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String param = request.getParameter("msg");
        String result = messageService.processRequest(param);

        out.println("<html><body><h1>Response</h1><p>" + result + "</p></body></html>");
    }
}