<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.example.model.*"%>
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
		Software Name : <select name="softwareName">
							<c:forEach items="${softwares}" var="s">
								<option value="${s.getName()}">${s.getName()}</option>
							</c:forEach>
						</select><br>
		Enter Reason : <input type="text" name ="reason"><br>
		<input type="submit">
	</form>
	<a href="Logout">Logout</a>
</body>
</html>