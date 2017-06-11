<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Common.AppCode.ViewTextContainer"%>
<%@ page import="Common.AppCode.CommonConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/app-ajax.js" type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	ViewTextContainer container = (ViewTextContainer) request.getAttribute(ViewTextContainer.RESULT);
%>
<title><%=ViewTextContainer.REGISTER_PAGE_TITLE%></title>
</head>
<body>

	<h1><%=ViewTextContainer.REGISTER_PAGE_WELCOME_WARNING_MESSAGE %></h1>

	<div id="alert"></div>
	<br id="registerResult_id"><br>

	<form action="RegisterServlet" method="post">
		Username: <input type="text" name="username" id="username_id" >
		<br />
		Password: <input type="password" name="password" id="password_id" >
		<br />
		Repeat Password: <input type="password" name="repeatPassword" id="repeatPassword_id">
		<br />
		Email: <input type="text" name="email" id="email_id" > 
		<br />
		Name: <input type="text" name="name" id="name_id" > 
		<br />
		Surname: <input type="text" name="surname" id="surname_id" > 
		<br />
		Gender:
		<select name="Gender" id="gender_id">
		
		<%
			for(int i=0; i < CommonConstants.GENDER.size(); i++)
			{
				out.print("<option>"+CommonConstants.GENDER.get(i)+"</option>");
			}
		%>
		</select>
		<br />
		<input onclick="register()" type='button' value='Register'>
		
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
		    	document.getElementById("alert").innerHTML = response;  
		    	window.location.href = "/index.jsp";
		    }
		});
			
	}
</script>
</html>