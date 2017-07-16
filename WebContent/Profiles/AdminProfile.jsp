
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Account.Models.*"%>
<%@ page import="Account.AppCode.*"%>
<%@ page import="Common.Models.*"%>
<%@ page import="Subject.Models.DbModels.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="../Subject/SubjectShareJS.js" ></script>
	
	<!-- <link rel="stylesheet" type="text/css" href="style.css"> -->
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	 		integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" 
			integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="jquery-ui.min.css">
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Admin Profile</title>
</head>

<%
	ResponseModel responseModel = (ResponseModel)request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE); 
	List<Subject> subjects = responseModel.getResultList();
%>

<body>
	<div class="w3-container w3-blue">
		<h1>Hello, Admin!</h1>
	</div>
	
	<br>
	<h3>Search Subject: </h3>
		<div class="form-group col-lg-3 container">
	<input id="subject_search" class="form-control" placeholder="Subject" type="text" name="subjectSearchInput">
	</div>
	<br>

	<form action="/AddSubjectPageGeneratorServlet" method="get">
		
		<input type="submit" class='btn btn-primary' type='button' value="Add Subject">
	</form>

	<br>
	
	<div class="w3-container w3-black">
		<h2>All Subjects:</h2>
		<div id="search_subject_list_id">
			<table class='table'>
				<thead>
					<tr>
						<th>
							Name
						</th>						
						
					</tr>
				</thead>
				<%
					for (Subject subject : subjects) {
						out.println("<tr id = 'subject_tr_"+subject.getId()+"' >");
						out.println("<td>");
						out.println("<label subject_name_label_id='"+subject.getId()+"' >" + subject.getName() + "</label>");
						out.println("</td>");
						out.println("<td>");
						out.print("<input value='Edit' class='btn btn-primary' type='button' onclick='EditSubject("+subject.getId()+");'>");
						out.print("<input value='Delete' class='btn btn-danger' type='button' onclick='DeleteSubject("+subject.getId()+");'>");
						out.println("</td>");
						out.println("</tr>");
					}
				%>
			</table>
		</div>
		
	</div>
</body>

<script>

function EditSubject(subjectId){
	redirectUrl = "EditSubjectPageGeneratorServlet?id="+subjectId;
	window.location = redirectUrl;
}

function DeleteSubject(subjectId){
	
	data = {
		subjectId : subjectId
	}
	
	$.ajax({
		type : "POST",
		url : "/DeleteSubjectServlet",
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(response) {
			$("#subject_tr_"+subjectId).remove();
		 }
	});
	
	
	
}

$(document).ready(function() {
	
	$("#subject_search").on("change paste keyup", function() {

		searchValue = $("#subject_search").val();
		
		data = {
			searchValue : searchValue
		}
		
		$.ajax({
			type : "POST",
			url : "/AdminSubjectSearchServlet",
			contentType : "application/json",
			data : JSON.stringify(data),
			success : function(response) {
				searchResults = response.resultObject.searchResultList;
				console.log(searchResults);
				if(searchResults.length == 0){
					searchResults=["No result"];
				}
				$("#subject_search").autocomplete({
				      source: searchResults
				    });
				$('#subject_search').on('keypress', function (e) {
			        if(e.which === 13){
						console.log("enterze daechira");
						console.log(response.resultList);
						generateSubjectTable(response.resultList)
			        }
				});
			 }
		});
	});
});

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
			console.log(args.subject.name)
			var tr = document.createElement('tr');
			tr.setAttribute("id", "subject_tr_"+args.subject.id);
			tr.setAttribute("class", "info");

			var nameTd = document.createElement('td');
			var name = document.createElement("label");
			name.setAttribute("id", "subject_name_label_id" + args.subject.id);
			name.innerHTML = args.subject.name;
			nameTd.append(name);

			var removeEditTd = document.createElement('td');
			
			var remove = document.createElement("INPUT");
			remove.setAttribute("type", "button");
			remove.setAttribute("value", "Delete");
			remove.setAttribute("onclick", "DeleteSubject(" + args.subject.id
					+ "); return false;");
			remove.setAttribute("class", "btn btn-danger");
			
			var edit = document.createElement("INPUT");
			edit.setAttribute("type", "button");
			edit.setAttribute("value", "Edit");
			edit.setAttribute("onclick", "EditSubject(" + args.subject.id
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


</script>

<style type="text/css">
	<jsp:include page="AdminProfile.css"/>
</style>

</html>