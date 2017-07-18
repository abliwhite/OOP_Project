<%@page import="Account.AppCode.AccountManager"%>
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

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>

<%
	User user = (User) request.getAttribute("user");
	UserProfile profile = user.getProfile();
	List<SubjectTerm> subjectTermsList = (ArrayList<SubjectTerm>) request.getAttribute("SubjectTerms");
	List<Subject> userSubjects = (ArrayList<Subject>)request.getAttribute("UserSubjects");
	List<Subject> allSubjects = (ArrayList<Subject>)request.getAttribute("AllSubjects");
%>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>

<body>
	<div class="w3-container w3-blue">
		<h1>Hello, <%=profile.getName()%>!</h1>
	</div>
	<br>
	
	<div class="container">
		<input type="hidden" id="hidden_user_id" value="<%=user.getId()%>">
		<h3>Add Subject:</h3>
		Subject: <select class="form-control" name="Subjects" id="subjects_id">
		<%
			Set<Integer> years = new HashSet<Integer>();
			for (Subject sub : allSubjects) {
				years.add(sub.getYear());
				out.print("<option value = '" + sub.getName() + "'>" + sub.getName() + "</option>");
			}
		%>
		</select>
			
		Year: <select class="form-control" name="SubjectYears" id="year_id">
		<%
			for (int year : years) {
				out.print("<option value = '" + year + "'>" + year + "</option>");
			}
		%>
		</select>
		
		Term: <select class="form-control" name="SubjectTerm" id="term_id">
		<%
			for (int i = 0; i < subjectTermsList.size(); i++) {
				SubjectTerm term = subjectTermsList.get(i);
				out.print("<option value = '" + term.getId() + "'>" + term.getName() + "</option>");
			}
		%>
		</select>
		<br>
		<input onclick="addUserSubject(); return false;" id="subject_add_Button_id" type='button' class='btn btn-primary' value='Add'>
		<br>
		<div style="display:none" class="alert alert-success" id="alert_div_id" role="alert"></div>
	</div>

	<br>

	<div class="container-fluid">
		<h2>All Subjects:</h2>
		<div id="search_subject_list_id">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>
							Name
						</th>						
						
					</tr>
				</thead>
				<%
					for (Subject subject : userSubjects) {
						out.println("<tr id = 'subject_tr_"+subject.getId()+"' >");
						out.println("<td>");
						out.println("<label subject_name_label_id='"+subject.getId()+"' >" + subject.getName() + "</label>");
						out.println("</td>");
						out.println("<td>");
						out.print("<input value='Edit' class='btn btn-primary' type='button' onclick='EditUserSubject("+subject.getId()+");'>");
						out.print("<input value='Delete' class='btn btn-danger' type='button' onclick='DeleteUserSubject("+subject.getId()+");'>");
						out.println("</td>");
						out.println("</tr>");
					}
				%>
			</table>
		</div>
		
	</div>
	

</body>

<script>

function EditUserSubject(subjectId){
	
}

function DeleteUserSubject(subjectId){
	
	userId = $("#hidden_user_id").val();
	
	data = {
			userId : userId,
			subjectId : subjectId
		}
		
	$.ajax({
		type : "POST",
		url : "/DeleteUserSubjectServlet",
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(response) {
			$("#subject_tr_"+subjectId).remove();
		 }
	});
	
}

function addUserSubject(){
	subjectName = $("#subjects_id").val();
	subjectYear = $("#year_id").val();
	subjectTermId = $("#term_id").val();
	userId = $("#hidden_user_id").val();
	
	console.log(subjectName);
	console.log(subjectYear);
	console.log(subjectTermId);
	console.log(userId);
		
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
			subjectTermId: subjectTermId,
			userId: userId
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