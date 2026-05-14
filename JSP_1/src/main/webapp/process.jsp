<%@ page import="java.util.*" %>

<%
    String name = (String) request.getAttribute("user_name");

    if(name == null || name.isEmpty()){
        name = "Guest";
    }

    String reversed = new StringBuilder(name).reverse().toString();

    int length = name.length();

    int vowels = 0;
    for(char c : name.toLowerCase().toCharArray()){
        if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
            vowels++;
        }
    }

    String ip = request.getRemoteAddr();
    String time = new Date().toString();
    String browser = request.getHeader("User-Agent");
    String method = request.getMethod();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
</head>
<body>

<h1>Welcome <%= name %>!</h1>

<h2>Processed Info:</h2>
<ul>
    <li>Reversed Name: <%= reversed %></li>
    <li>Name Length: <%= length %></li>
    <li>Vowel Count: <%= vowels %></li>
</ul>

<h2>Server Info:</h2>
<ul>
    <li><b>IP Address:</b> <%= ip %></li>
    <li><b>Browser:</b> <%= browser %></li>
    <li><b>Request Method:</b> <%= method %></li>
    <li><b>Server Time:</b> <%= time %></li>
</ul>

<br>
<a href="index.jsp">Go Back</a>

</body>
</html>