<%
    // Get username from form
    String user = request.getParameter("username");

    // 1. SESSION TRACKING
    // Store username in session (Server-side storage)
    session.setAttribute("user", user);

    // 2. COOKIE HANDLING
    // Create cookie object (Client-side storage)
    Cookie ck = new Cookie("username", user);

    // Set cookie expiry time (1 hour)
    ck.setMaxAge(60 * 60);

    // Add cookie to response
    response.addCookie(ck);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Set Data</title>
</head>
<body>
    <h2>Data Stored Successfully!</h2>
    <p>The username <b><%= user %></b> is now saved.</p>
    <hr>
    <!-- Link to the "final" page to see if it worked -->
    <a href="display.jsp">Go to Final Display Page</a>
</body>
</html>