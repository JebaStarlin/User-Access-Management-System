<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(session.getAttribute("user")==null){
			System.out.println("rejected");
			response.sendRedirect("login.jsp");
		}
	%>
	
	<h1> Welcome ${user.getUsername()}</h1>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Software name</th>
				<th>Description</th>
				<th>Access Level</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${softwares}" var="s">
			<tr>
				<td>${s.getId()}</td>
				<td>${s.getName()}</td>
				<td>${s.getDescription()}</td>
				<td>${s.getAccessLevels()}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%
        if (!session.getAttribute("role").equals("Manager")) {
    %>
        <a href="requestAccess.jsp">Request Access</a><br>

    <%
        } 
	%>	
	<%
        if (!session.getAttribute("role").equals("Employee")) {
    %>
        <a href="RequestServlet">Requests</a><br>
    <%
        } 
	%>
	
	<%
        if (session.getAttribute("role").equals("Admin")) {
    %>
        <a href="createSoftware.jsp">Create New Software</a>
    <%
        } 
	%>
	<br>
	<a href="Logout">Logout</a>
</body>
</html>