<%@page import="Account.AppCode.AccountManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Account.Models.*"%>
<%@ page import="Common.Models.*"%>
<%@ page import="Account.AppCode.*"%>
<%@ page import="Profiles.ProfileViewModels.*"%>
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
ResponseModel responseModel = (ResponseModel)request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);
StudentProfileViewModel  spViewModel = (StudentProfileViewModel)responseModel.getResultObject();
List<Subject> userSubjects = spViewModel.getUserSubjects();
%>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>

<body>
	<div class="w3-container w3-blue">
		<h1>Hello, <%=spViewModel.getUser().getProfile().getName()%>!</h1>
	</div>
	<br>
	
	<div class="container">
		<input type="hidden" id="hidden_user_id" value="<%=spViewModel.getUser().getId()%>">
		<h3>Add Subject:</h3>
		Subject: <select class="form-control" name="Subjects" id="subjects_id">
		<%
			List<UserSubjectDropDownViewModel> allSubjects = spViewModel.getAllSubjects();
			for (UserSubjectDropDownViewModel sub : allSubjects) {
				
				out.print("<option value = '" + sub.getSubjectId() + "'>" + sub.getSubjectIdentityString()+ "</option>");
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
	redirectUrl = "EditUserSubjectPageGenerator?id="+subjectId;
	window.location = redirectUrl;
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

function generateSubjectTable(list){
	var templatesDiv = $("#search_subject_list_id");

	templatesDiv.empty();

	var templates = document.createElement('table');

	templates.setAttribute("class", "table");
	var thead = document.createElement('thead');
	// thead.setAttribute("class","thead-inverse");
	var tr1 = document.createElement('tr');
	var th1 = document.createElement('th');
	th1.innerText = "Name";

	tr1.append(th1);

	thead.append(tr1);

	templates.append(thead);

	list.forEach(function createComponentTemplateTableRow(args) {
		var tr = document.createElement('tr');
		tr.setAttribute("id", "subject_tr_"+args.id);
		tr.setAttribute("class", "info");

		var nameTd = document.createElement('td');
		var name = document.createElement("label");
		name.setAttribute("id", "subject_name_label_id" + args.id);
		name.innerHTML = args.name;
		nameTd.append(name);

		var removeEditTd = document.createElement('td');
		
		var remove = document.createElement("INPUT");
		remove.setAttribute("type", "button");
		remove.setAttribute("value", "Delete");
		remove.setAttribute("onclick", "DeleteUserSubject(" + args.id
				+ "); return false;");
		remove.setAttribute("class", "btn btn-danger");
		
		var edit = document.createElement("INPUT");
		edit.setAttribute("type", "button");
		edit.setAttribute("value", "Edit");
		edit.setAttribute("onclick", "EditUserSubject(" + args.id
				+ "); return false;");
		edit.setAttribute("class", "btn btn-primary");
		
		removeEditTd.append(edit);
		removeEditTd.append(remove);	
		
		tr.append(nameTd);
		tr.append(removeEditTd);

		templates.append(tr);
	});

	templatesDiv.append(templates);
}

function addUserSubject(){
	subjectId = $("#subjects_id").val();
	userId = $("#hidden_user_id").val();
	
	data = {
			subjectId: subjectId,			
			userId: userId
		};
		
	$.ajax({
		type: "POST",
		url: "/AddUserSubjectServlet",
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(response) {
			console.log(response.resultList);
			generateSubjectTable(response.resultList);
		}
	});
}
	
</script>

</html>