<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js"
	type="text/javascript"></script>
	<script type="text/javascript" src="../shareJS.js" ></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<title>Home Page</title>
</head>

<body>
	<h1 class="text-center">Welcome</h1>
	
	<div class="form-group col-lg-3 container alert alert-danger" style="display:none" id="login_alert_div_id" role="alert">
	</div>
	<div class="form-group col-lg-3 container">

		<br>Username:<br> <input type="text"
			class="form-control input-sm" placeholder="Username" name="username"
			id="username_id">
	</div>
	<div class="form-group col-lg-3 container">

		Password:<input type="password" class="form-control input-sm"
			placeholder="Password" name="Password" id="password_id">
	</div>
	<div class="text-center">
		<input type="button" onclick="login()" value="Login"
			class="btn btn-success"> <input type="button"
			onclick="redirect()" value="Register" class="btn btn-primary">
	</div>



</body>
</html>
<script>
	function redirect() {
		$(location).attr('href', "/Registration/Register.jsp");
	}

	function login() {
		var username = $('#username_id').val();
		var password = $('#password_id').val();

		var data = {
			username : username,
			password : password
		};

		$.ajax({
			type : "POST",
			url : "/LoginServlet",
			contentType : "application/json",
			data : JSON.stringify(data),
			success : function(response) {
				
				if(!response.isSuccess){
					$("#login_alert_div_id").html(response.resultMessage);
					$("#login_alert_div_id").show();
					fadeAlertMessage("login_alert_div_id");
					return;
				}
				
				if(response.resultMessage == "Admin"){
					$(location).attr('href', "/AdminProfilePageGeneratorServlet");
				}else{
					$(location).attr('href', "/StudentProfilePageGeneratorServlet?id="+response.resultObject);
					
				}
				
			}
		});
	}
</script>