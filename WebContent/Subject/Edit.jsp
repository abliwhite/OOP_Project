<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Subject.Models.SubjectViewEntity"%>
<%@ page import="Subject.Models.SubjectComponentTemplatesViewEntity"%>
<%@ page import="Subject.Models.Subject"%>
<%@ page import="java.util.List"%>    


   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
	
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
	SubjectViewEntity viewModel = (SubjectViewEntity)request.getAttribute(SubjectViewEntity.SUBJECT_VIEW_ENTITY_ATTRIBUTE);
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
		out.print("language: <input type='text' name='language' class='form-control' placeholder='language' id='language_id' value='"+subject.getLanguage()+"' >");
		out.print("Ects: <input type='text' name='ects' class='form-control' placeholder='ects' id='ects_id' value='"+subject.getEcts()+"' >");
		out.print("LecturerName: <input type='text' name='lecturerName' class='form-control' placeholder='lecturerName' id='lecturerName_id' value='"+subject.getLecturerName()+"' >");
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
				List<SubjectComponentTemplatesViewEntity> temp = viewModel.getSubjecComponentTemplatesViewEnties();
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

	function addTemplate(){
		componentName = $("#subjectComponentNameInput_id").val();
		componentPercentage = $("#subjectComponentTemplatePercentageInput_id").val();
		componentNumber = $("#subjectComponentTemplateNumberInput_id").val();
		subjectId = $("#hidden_subject_id").val();
		
		if(componentName == "" || componentPercentage == "" || componentNumber == ""){
			$("#alert").innerHTML = "Fill All Fields";
			return;
		}
		
		data = {
				subjectId: subjectId,			
				name: componentName,
				percentage: componentPercentage,
				number: componentNumber	
			};
		
		$.ajax({
		    type: "POST",
		    url: "/AddComponentTemplateServlet",
		    contentType: "application/json",
		    data: JSON.stringify(data),
		    success: function(response) {
		    	buildNewComponentTemplateTable(response);
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
	
	function deleteComponentTemplate(id){
		subjectId = $("#hidden_subject_id").val();
		
		data = {
				id: id,	
				subjectId: subjectId
			};
		
		$.ajax({
		    type: "POST",
		    url: "/DeleteComponentTemplateServlet",
		    contentType: "application/json",
		    data: JSON.stringify(data),
		    success: function(response) {
		    	//buildNewComponentTemplateTable(response);
		    	$("#"+id).remove();
		    }
		});
	}
	
	function buildNewComponentTemplateTable(arg){
		var templatesDiv = $("#subjectComponents_id");
		
		templatesDiv.empty();
		
		var templates = document.createElement('table');
			
		templates.setAttribute("class","table");
		var thead = document.createElement('thead');
		//thead.setAttribute("class","thead-inverse");
		var tr1 = document.createElement('tr');
		var th1 = document.createElement('th');
		th1.innerText = "Name";
		var th2 = document.createElement('th');
		th2.innerText = "Number";
		var th3 = document.createElement('th');
		th3.innerText = "Percent";
		
		tr1.append(th1);
		tr1.append(th2);
		tr1.append(th3);
		
		thead.append(tr1);
		
		templates.append(thead);
		
		
		
		arg.forEach(function createComponentTemplateTableRow(args){
			console.log(args);
			console.log(args.id)
			var tr = document.createElement('tr');
			tr.setAttribute("id",args.id);
			tr.setAttribute("class","info");
			
			var nameTd = document.createElement('td');
			var name = document.createElement("INPUT");
			name.setAttribute("type", "text");
			name.setAttribute("value",args.name);
			name.setAttribute("id","subjectComponentNameInput_"+args.id);
			name.setAttribute("class","form-control");
			name.setAttribute("placeholder","Name");
			nameTd.append(name);
			
			var numberTd = document.createElement('td');
			var number = document.createElement("INPUT");
			number.setAttribute("type", "number");
			number.setAttribute("value",args.number);
			number.setAttribute("id","subjectComponentTemplateNumberInput_"+args.id);
			number.setAttribute("class","form-control");
			number.setAttribute("placeholder","Number");
			numberTd.append(number);
			
			var percentageTd = document.createElement('td');
			var percentage = document.createElement("INPUT");
			percentage.setAttribute("type", "number");
			percentage.setAttribute("value",args.markPercentage);
			percentage.setAttribute("id","subjectComponentTemplatePercentageInput_"+args.id);
			percentage.setAttribute("class","form-control");
			percentage.setAttribute("placeholder","Percentage");
			percentageTd.append(percentage);		
			
			var removeEditTd = document.createElement('td');
			var edit = document.createElement("INPUT");
			edit.setAttribute("type", "button");
			edit.setAttribute("value","Edit");
		    edit.setAttribute("onclick","EditTemplate("+args.id+"); return false;");
		    edit.setAttribute("class", "btn btn-warning");
		    removeEditTd.append(edit);
		    
		    var remove = document.createElement("INPUT");
		    remove.setAttribute("type", "button");
		    remove.setAttribute("value","Delete");
		    remove.setAttribute("onclick","deleteComponentTemplate("+args.id+"); return false;");
			remove.setAttribute("class", "btn btn-danger");
			removeEditTd.append(remove);
			
	        tr.append(nameTd);
	        tr.append(numberTd);
	        tr.append(percentageTd);
	        tr.append(removeEditTd);

	        templates.append(tr);
		});
		
		templatesDiv.append(templates);
	}
	
	

	
</script>

</html>