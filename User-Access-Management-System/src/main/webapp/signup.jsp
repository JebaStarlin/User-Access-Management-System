<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="SignUp" method="post">
		Enter Username :<input type="text" name="username"><br>
		Enter Password :<input type="password" name="password"><br>
		Enter Role : <select name="role">
						<option value="Employee">Employee</option>
						<option value="Manager">Manager</option>
						<option value="Admin">Admin</option>
					 </select><br>
		<input type="submit" value="SignUp">
	</form>	
</body>
</html>