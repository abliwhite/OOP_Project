<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Account.Models.*"%>
<html>

	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<% 
			User user = (User)request.getAttribute("user");
			UserProfile profile = user.getProfile();
			
		%>
		<title>Welcome</title>
	</head>
	
	<body>
		<h1>Hello, <%=profile.getName()%>!</h1>
			
		<br>

		<div class="container">
		Search Subject: <input type='text' name='name' class="form-control" placeholder="Enter Subject" id='name_id'>
		<input onclick="AddSubject(); return false;" id="subject_add_Button_id" type='button' class='btn btn-primary' value='Add'>
	</div>
	
	
	
	</body>
	
	
</html>