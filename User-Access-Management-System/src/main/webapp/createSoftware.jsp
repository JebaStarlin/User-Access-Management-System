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
		if(!user.getRole().equals("Admin")){
			out.print("You are not allowed to access this");
			response.sendRedirect("showSoftwares.jsp");
		}
	%>
	
	<form action="SoftwareServlet" method="post">
		Enter Software name : <input type="text" name="name"><br>
		Enter Software Description : <input type="text" name="description"><br>
		Enter Access Level : <select name="access">
								<option value="Read">Read</option>
								<option value="Write">Write</option>
								<option value="Admin">Admin</option>
							 </select><br>
		<input type="submit" value="ADD">
	</form>
	<a href="Logout">Logout</a>
</body>
</html>