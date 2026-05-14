<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Hello JSP </title>
<meta charset="UTF-8">
</head>
<body>
<%
out.println("IP address is:"+request.getRemoteAddr());
%>
</body>
</html>