package def;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class auth extends HttpServlet {

    // We use doPost because passwords should be sent via POST requests, not GET.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        // 1. Retrieve user inputs
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // 2. CONTEXT INTERFACE: Accessing application-wide data
        ServletContext context = getServletContext();
        Integer totallogins = (Integer) context.getAttribute("loginCount");
        if (totallogins == null) {
            totallogins = 0;
        }

        // Set response type before writing to it
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Simple validation logic
        if ("admin".equals(user) && "password123".equals(pass)) {
            // Update Context Attribute (shared by all users)
            context.setAttribute("loginCount", ++totallogins);

            out.println("<!DOCTYPE html>");
            out.println("<html><body>");
            out.println("<h2 style='color:green'>Login Successful!</h2>");
            out.println("<p>Welcome, " + user + "</p>");
            out.println("<p>Total successful logins in this app: " + totallogins + "</p>");
            out.println("</body></html>");
        } else {
            // 3. ERROR HANDLING: Triggering a specific HTTP Error code
            // This will be caught by the <error-page> setting in web.xml
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Username or Password");
        }
    }
}