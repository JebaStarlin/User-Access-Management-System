<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.example.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		User user = (User)session.getAttribute("user");
		if(user.getRole().equals("Manager")){
			out.print("You are not allowed to access this");
			response.sendRedirect("showSoftwares.jsp");
		}
	%>
	<form action="RequestServlet" method="post">
		Enter Software Id: <input type="number" name= "softwareId"><br>
		Enter Reason : <input type="text" name ="reason"><br>
		<input type="submit">
	</form>
	<a href="Logout">Logout</a>
</body>
</html>