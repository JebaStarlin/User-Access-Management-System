<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.example.model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		User user = (User)session.getAttribute("user");
		if(user.getRole().equals("Employee")){
			out.print("You are not allowed to access this");
			response.sendRedirect("showSoftwares.jsp");
		}
	%>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>User-Id</th>
				<th>Software-Id</th>
				<th>accessType</th>
				<th>reason</th>
				<th>status</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${requests}" var="s">
			<tr>
				<td>${s.getId()}</td>
				<td>${s.getUserId()}</td>
				<td>${s.getSoftwareId()}</td>
				<td>${s.getAccessType()}</td>
				<td>${s.getReason()}</td>
				<td>${s.getStatus()}</td>
				
		        <c:choose>
		            <c:when test="${s.status == 'Pending'}">
		                <td>
		                    <form action="ApprovalServlet" method="post">
		                        <input type="hidden" name="adminAction" value="approved">
		                        <input type="hidden" name="id" value="${s.getId()}">
		                        <input type="submit" value="Approve"> 
		                    </form>
		                </td>
		                <td>
		                    <form action="ApprovalServlet" method="post">
		                        <input type="hidden" name="adminAction" value="rejected">
		                        <input type="hidden" name="id" value="${s.getId()}">
		                        <input type="submit" value="Reject"> 
		                    </form>
		                </td>
		            </c:when>
		        </c:choose>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<a href="Logout">Logout</a>
</body>
</html>