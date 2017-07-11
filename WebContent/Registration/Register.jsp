<%@page import="Database.DbCertificate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Common.AppCode.ViewTextContainer"%>
<%@ page import="Common.AppCode.CommonConstants" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>

<!-- <link rel="stylesheet" type="text/css" href="style.css"> -->
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	 		integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" 
			integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	ViewTextContainer container = (ViewTextContainer) request.getAttribute(ViewTextContainer.RESULT);
%>
<title><%=ViewTextContainer.REGISTER_PAGE_TITLE%></title>
</head>
<body>

	<h1><%=ViewTextContainer.REGISTER_PAGE_WELCOME_WARNING_MESSAGE %></h1>

	<div id="alert"></div>

	<form action="RegisterServlet" method="post">
	<div class="container">
		Username: <input type="text" class="form-control" placeholder="Username" name="username" id="username_id" >
		Password: <input type="password" class="form-control" placeholder="Ects" name="Password" id="password_id" >
		Repeat Password: <input type="password" class="form-control" placeholder="Repeat Password" name="repeatPassword" id="repeatPassword_id">
		Email: <input type="email" class="form-control" placeholder="Email" name="email" id="email_id" > 
		Name: <input type="text" class="form-control" placeholder="Name" name="name" id="name_id" > 
		Surname: <input type="text" class="form-control" placeholder="Surname" name="surname" id="surname_id" > 
		Gender:
		<select class="form-control" name="Gender" id="gender_id">
		<%
			for(int i=0; i < DbCertificate.ProfileTable.GENDER.size(); i++)
			{
				out.print("<option>"+DbCertificate.ProfileTable.GENDER.get(i)+"</option>");
			}
		%>
		</select>
		<br />
		<input onclick="register(); return false;" type='button' class='btn .btn-success' value='Sign Up'>
		</div>
	</form>
	
	
</body>
<script>
	function register() {
		var username = document.getElementById("username_id").value;
		var pass = document.getElementById("password_id").value;
		var repeatPass = document.getElementById("repeatPassword_id").value;
		var email = document.getElementById("email_id").value;
		var name = document.getElementById("name_id").value;
		var surname = document.getElementById("surname_id").value;
		var gender = document.getElementById("gender_id").value;
		
		if (username == '' || pass == '' || email == '' || name == '' || surname == '') {
			document.getElementById("alert").innerHTML = "Fill All Fields";
			return;
		}
		if(pass.length <=3){
			document.getElementById("alert").innerHTML = "Password length must be at least 3";
			return;
		}
		if(pass != repeatPass){
			document.getElementById("alert").innerHTML = "Password doesn't match";
			return;
		}
		
		var data = {
			"username": username,
			"password": pass,
			"email": email,
			"name": name,
			"surname": surname,
			"gender": gender
		};
		
		
		$.ajax({
		    type: "POST",
		    url: "/RegisterServlet",
		    contentType: "application/json",
		    data: JSON.stringify(data),
		    success: function(response) {
		    	console.log(response);
		    	document.getElementById("alert").innerHTML = response;  
		    	if(response == "Success!"){
		    		window.location.href = "index.jsp";	
		    	}
		    }
		});
			
	}
</script>
</html>