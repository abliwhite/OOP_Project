<%@page import="Account.AppCode.AccountManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Account.Models.*"%>
<%@ page import="Account.AppCode.*"%>
<%@ page import="Subject.Models.SubjectTerm"%>
<%@ page import="java.util.List"%>  
<%@ page import="java.util.ArrayList"%> 
<html>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	User user = (User) request.getAttribute("user");
	UserProfile profile = user.getProfile();
	List<SubjectTerm> subjectTermsList = (ArrayList<SubjectTerm>) request.getAttribute("SubjectTerms");
%>

<title>Welcome</title>
</head>

<body>
	<h1>
		Hello,
		<%=profile.getName()%>!
	</h1>

	<br>
	<div class="container">
		<h3>Add Subject:</h3>
		Subject: <input type='text' name='name' class="form-control" placeholder="Enter Subject" id='name_id'> 
		Year: <input type='text' name='name' class="form-control" placeholder="Enter Year" id='year_id'>
		Term: 
		<select class="form-control" name="SubjectTerm" id="term_id">
		<%
			for (int i = 0; i < subjectTermsList.size(); i++) {
				SubjectTerm term = subjectTermsList.get(i);
				out.print("<option value = '" + term.getId() + "'>" + term.getName() + "</option>");
			}
		%>
		</select>
		<input onclick="addUserSubject(); return false;" id="subject_add_Button_id" type='button' class='btn btn-primary' value='Add'>
		<br>
		<div style="display:none" class="alert alert-success" id="alert_div_id" role="alert">
	</div>

	<br>

	<h2>Your Subjects:</h2>

</body>

<script>
function addUserSubject(){
	subjectName = $("#name_id").val();
	subjectYear = $("#year_id").val();
	subjectTermId = $("#term_id").val();
		
	if (subjectName == "" || subjectYear == "" || subjectTermId == ""){
		$("#alert_div_id").removeClass("alert alert-success");
		$("#alert_div_id").addClass("alert alert-danger");
		$("#alert_div_id").html("Fill All Fields!");
    	$("#alert_div_id").show();
    	return;
	}
		
	data = {
			subjectName: subjectName,			
			subjectYear: subjectYear,
			subjectTermId: subjectTermId
		};
		
	$.ajax({
		type: "POST",
		url: "/AddUserSubjectServlet",
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(response) {
		    buildNewComponentTemplateTable(response);
		}
	});
}
	
</script>

</html>