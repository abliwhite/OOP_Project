<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Account.Models.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<% 
			User user = (User)request.getAttribute("user");
			UserProfile profile = user.getProfile();
		%>
		<title>Welcome <%=profile.getName()%></title>
	</head>
	
	<body>
		<h4>Logged in as <%= user.getUsername() %></h4>
		
		<br>
		
		<h2>Hello, <%=profile.getName()%>!</h2>
	</body>
</html>