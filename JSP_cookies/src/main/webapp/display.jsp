<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Final Display</title>
</head>
<body>
    <h2>Final Retrieval Page</h2>

    <%-- 1. Get data from SESSION --%>
    <h3>From Session:</h3>
    <p>Welcome, <%= session.getAttribute("user") %></p>

    <hr>

    <%-- 2. Get data from COOKIES --%>
    <h3>From Cookies:</h3>
    <%
        Cookie[] cookies = request.getCookies();
        String cookieName = "Not Found";
        
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    cookieName = c.getValue();
                }
            }
        }
        out.print("Cookie Value: " + cookieName);
    %>
    
    <br><br>
    <a href="login.jsp">Back to Login</a>
</body>
</html>