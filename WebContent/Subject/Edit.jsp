<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Subject.Models.ViewModels.SubjectViewModel"%>
<%@ page import="Subject.Models.ViewModels.SubjectComponentTemplatesViewModel"%>
<%@ page import="Subject.Models.DbModels.Subject"%>
<%@ page import="java.util.List"%>    


   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="../Subject/SubjectShareJS.js" ></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	 		integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" 
			integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>Edit Subject</title>
<% 
	SubjectViewModel viewModel = (SubjectViewModel)request.getAttribute(SubjectViewModel.SUBJECT_VIEW_ENTITY_ATTRIBUTE);
	Subject subject = viewModel.getSubject();
%>

<body>
	<h1 id="alert"></h1>
	<div class="container">
		<div style="display:none">
			<%
			out.print("<input type='hidden' id='hidden_subject_id' value='"+subject.getId()+"' >");
			%>
		</div>
		<%
		out.print("Name: <input type='text' name='name' class='form-control' placeholder='Name' id='name_id' value='"+subject.getName()+"' >");
		//out.print("language: <input type='text' name='language' class='form-control' placeholder='language' id='language_id' value='"+subject.getLanguage()+"' >");
		//out.print("Ects: <input type='text' name='ects' class='form-control' placeholder='ects' id='ects_id' value='"+subject.getEcts()+"' >");
		//out.print("LecturerName: <input type='text' name='lecturerName' class='form-control' placeholder='lecturerName' id='lecturerName_id' value='"+subject.getLecturerName()+"' >");
		%>
		<input onclick="EditSubject('<%=subject.getId()%>'); return false;" class='btn btn-warning' type='button' value='Edit'>
	</div>
	<div style="display:none" class="alert alert-success" id="alert_div_id" role="alert">
	</div>
	<div>
		<div id = "subjectComponents_id">
			<table class='table'>
				<thead>
					<tr>
						<th>
							Name
						</th>						
						<th>
							Number
						</th>
						<th>
							Percent
						</th>
					</tr>
				</thead>
			<%
				List<SubjectComponentTemplatesViewModel> temp = viewModel.getSubjecComponentTemplatesViewEnties();
				for(int i = 0;i < temp.size();i++){
					int componentId = temp.get(i).getSubjectComponentTemplate().getId();
					String componentName = temp.get(i).getSubjectComponentTemplate().getName();
					int componentNumber = temp.get(i).getSubjectComponentTemplate().getNumber();
					double componentPercentage = temp.get(i).getSubjectComponentTemplate().getMarkPercentage();
					out.println("<tr id = '"+componentId+"' >");
					out.println("<td>");
					out.println("<input type='text' class='form-control' placeholder='Name' id='subjectComponentNameInput_"+componentId+"' value='"+componentName+"'>");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type='number' class='form-control' placeholder='Number' id='subjectComponentTemplateNumberInput_"+componentId+"' value='"+componentNumber+"'>");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type='number' class='form-control' placeholder='Percent' id='subjectComponentTemplatePercentageInput_"+componentId+"' value='"+componentPercentage+"'>");
					out.println("</td>");
					out.println("<td>");
					out.println("<input type='button' class='btn btn-warning' value='Edit' onclick='EditTemplate("+componentId+");' >");
					out.println("<input type='button' class='btn btn-danger' value='Delete' onclick='deleteComponentTemplate("+componentId+");' >");
					out.println("</td>");
					out.println("</tr>");
				}
			%>
			</table>
		</div>
		<div id="subjectComponentTemplateRow_id" >
			Add Subject Component: <input type="text" name="subjectComponentTemplateName" class="form-control" placeholder="Name" id="subjectComponentNameInput_id">
			<input type="number" name="subjectComponentTemplatePercentage" class="form-control" placeholder="Percent"  id="subjectComponentTemplatePercentageInput_id">
			<input type ="number" name ="subjectComponentTemplateNumber" class="form-control" placeholder="Number" id="subjectComponentTemplateNumberInput_id">
			<input onclick="addTemplate(); return false;" type='button' class='btn btn-primary' value='Add Template'>
		</div>
	</div>	
		

</body>

<script>
	function EditSubject(id){
		name = $('#name_id').val();
		language = $('#language_id').val();
		ects = $('#ects_id').val();
		lecturerName = $('#lecturerName_id').val();
		
		if(name == "" || language == "" || ects == "" || lecturerName == ""){
			$("#alert_div_id").removeClass("alert alert-success");
			$("#alert_div_id").addClass("alert alert-danger");
			$("#alert_div_id").html("Fill All Fields!");
	    	$("#alert_div_id").show();
			return;
		}
		
		data = {
				name: name,			
				language: language,
				ects: ects,
				lecturerName: lecturerName,
				subjectId: id
			};
		
		$.ajax({
		    type: "POST",
		    url: "/EditSubjectServlet",
		    contentType: "application/json",
		    data: JSON.stringify(data),
		    success: function(response) {
		    	$("#alert").InnerHTML = "Subject Was Successfully Updated"
		    	$("#alert_div_id").removeClass("alert alert-danger");
		    	$("#alert_div_id").addClass("alert alert-success");
		    	$("#alert_div_id").html("Success!");
		    	$("#alert_div_id").show();
		    }
		});
		
		
	}

	

	function EditTemplate(id) {
		componentName = $("#subjectComponentNameInput_"+id).val();
		componentPercentage = $("#subjectComponentTemplatePercentageInput_"+id).val();
		componentNumber = $("#subjectComponentTemplateNumberInput_"+id).val();
		
		data = {
			id: id,			
			name: componentName,
			percentage: componentPercentage,
			number: componentNumber,
		};
	
		$.ajax({
		    type: "POST",
		    url: "/EditComponentTemplateServlet",
		    contentType: "application/json",
		    data: JSON.stringify(data),
		    success: function(response) {
		    	buildNewComponentTemplateTable(response);
		    }
		});
		
	}
	
	
	
	

	
</script>

</html>