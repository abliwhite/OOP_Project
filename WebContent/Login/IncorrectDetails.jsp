<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Common.AppCode.*" %>


<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<head>
		<title>Home Page</title>
	</head>
	
	<body>
		<h1><%=((ViewTextContainer)request.getAttribute(ViewTextContainer.RESULT)).LOGIN_HEAD_VALIDATION_ERROR%></h1>
		<form action="LoginServlet" method="post">
		  <br><%=((ViewTextContainer)request.getAttribute(ViewTextContainer.RESULT)).LOGIN_VALIDATION_ERROR%><br>
		  <br>Username:<br>
		  <input type="text" name="name">
		  <br>Password:<br>
		  <input type="password" name="password">
		  <input type="submit" value="Login">
		</form>
		
		<br>
		
		<form action="LoginServlet" method="post">
		  <input type="submit" value="Login with facebook">
		</form>
		
		<br>
		
		<form action="RegistrationServlet" method="post">
		  <input type="submit" value="Register">
		</form>
	</body>
</html>