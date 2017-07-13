
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Account.Models.*"%>
<%@ page import="Account.AppCode.*"%>
<%@ page import="Subject.Models.DbModels.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../Subject/SubjectJS.js"></script>

<!-- <link rel="stylesheet" type="text/css" href="style.css"> -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Admin Profile</title>
</head>



<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">

<body>
	<div class="w3-container w3-blue">
		<h1>Hello, Admin!</h1>
	</div>
	
	<form action="" method="post">
		<br>
		<h3>Search Subject: </h3><input type="text" name="subjectSearchInput">
		<input type="submit" value="Search">
	</form>

	<br>

	<form action="/Subject/Add.jsp" method="get">
		<input type="submit" value="Add Subject">
	</form>

	<br>
	
	<div class="w3-container w3-black">
		<h2>All Subjects:</h2>
		<%
			List<Subject> allSubjects = (ArrayList<Subject>)request.getAttribute("AllSubjects");
			for (Subject subject : allSubjects) {
				out.print("<h3> <a href= ''>" + subject.getName() + "</a> </h3>");
			}
		%>
	</div>
</body>
</html>