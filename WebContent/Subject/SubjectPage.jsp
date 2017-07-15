<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Account.Models.*"%>
<%@ page import="Account.AppCode.*"%>
<%@ page import="Subject.Models.DbModels.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%
	Subject subject = (Subject)request.getAttribute("subject");
%>

<title></title>
</head>
<body>
<h1><%=subject == null%></h1>

</body>
</html>