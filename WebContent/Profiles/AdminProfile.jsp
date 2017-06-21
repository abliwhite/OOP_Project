<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Profile</title>
</head>

<body>
	<form action="" method="post">
		<br>Search Subject: <input type="text" name="subjectSearchInput">
		<input type="submit" value="Search">
	</form>

	<br>

	<form action="/SubjectAddEditServlet?type=add" method="get">
		<input type="submit" value="Add Subject">
	</form>

	<br>All Subjects:
</body>
</html>