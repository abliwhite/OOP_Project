<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Common.AppCode.*" %>


<html>

	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
	
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
		
		<form action="LoginServlet" method="post">
		  <input type="submit" value="Login with facebook">
		</form>

		<form action="Registration/Register.jsp" method="post">
		  <input type="submit" value="Register">
		</form>
	</body>
</html>