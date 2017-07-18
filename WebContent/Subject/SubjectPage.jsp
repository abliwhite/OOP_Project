<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Account.Models.*"%>
<%@ page import="Account.AppCode.*"%>
<%@ page import="Subject.Models.DbModels.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>       
<%@ page import="java.util.ArrayList"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%
	Subject subject = (Subject)request.getAttribute("subject");
	List<User> classmates = (List<User>)request.getAttribute("classmates");
%>

<title><%=subject.getName()%>  <%=subject.getYear()%></title>
</head>
<body>
<h1><%=subject.getName()%> <%=subject.getYear()%></h1>
<h2>Classmates:</h2>

<%
	for (User user : classmates) {
		out.print("<h1>" + user.getUsername() + "</h1>");
	}
%>
</body>
</html>