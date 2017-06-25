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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Subject</title>
<% 
SubjectViewEntity viewModel = (SubjectViewEntity)request.getAttribute(SubjectViewEntity.SUBJECT_VIEW_ENTITY_ATTRIBUTE);
Subject subject = viewModel.getSubject();
%>

<body>
	<h1 id="alert"></h1>
	<div>
		<div style="display:none">
			<%
			out.print("<input type='hidden' id='hidden_subject_id' value='"+subject.getId()+"' >");
			%>
		</div>
		<%
		out.print("Name: <input type='text' name='name' id='name_id' value='"+subject.getName()+"' ><br />");
		out.print("language: <input type='text' name='language' id='language_id' value='"+subject.getLanguage()+"' ><br />");
		out.print("Ects: <input type='text' name='ects' id='ects_id' value='"+subject.getEcts()+"' ><br />");
		out.print("LecturerName: <input type='text' name='lecturerName' id='lecturerName_id' value='"+subject.getLecturerName()+"' ><br />");
		%>
		<input onclick="EditSubject('<%=subject.getId()%>'); return false;" type='button' value='Edit'>
	</div>
	<div>
		<div id = "subjectComponents_id">
			<table>
			<%
				List<SubjectComponentTemplatesViewEntity> temp = viewModel.getSubjecComponentTemplatesViewEnties();
				for(int i = 0;i < temp.size();i++){
					int componentId = temp.get(i).getSubjectComponentTemplate().getId();
					String componentName = temp.get(i).getSubjectComponentTemplate().getName();
					int componentNumber = temp.get(i).getSubjectComponentTemplate().getNumber();
					double componentPercentage = temp.get(i).getSubjectComponentTemplate().getMarkPercentage();
					out.println("<tr>");
					out.println("<input type='text' id='subjectComponentNameInput_"+componentId+"' value='"+componentName+"'>");
					out.println("<input type='number' id='subjectComponentTemplateNumberInput_"+componentId+"' value='"+componentNumber+"'>");
					out.println("<input type='number' id='subjectComponentTemplatePercentageInput_"+componentId+"' value='"+componentPercentage+"'>");
					
					out.println("<input type='button' value='Edit' onclick='EditTemplate("+componentId+");' >");
					out.println("<input type='button' value='Delete' onclick='deleteComponentTemplate("+componentId+");' >");
					out.println("</tr>");
				}
			%>
			</table>
		</div>
		<div id="subjectComponentTemplateRow_id">
			Subject Component Template: <input type="text" name="subjectComponentTemplateName" id="subjectComponentNameInput_id">
			<input type="number" name="subjectComponentTemplatePercentage"  id="subjectComponentTemplatePercentageInput_id">
			<input type ="number" name ="subjectComponentTemplateNumber" id="subjectComponentTemplateNumberInput_id">
			<input onclick="addTemplate(); return false;" type='button' value='Add Template'>
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
			$("#alert").innerHTML = "Fill All Fields";
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
		    	
		    }
		});
	}
	
	function buildNewComponentTemplateTable(arg){
		var templatesDiv = document.getElementById("subjectComponents_id");
		//ar mushaobda jquerit todo 
		
		//seminaris kodi
		if (templatesDiv.childNodes.length > 0) {
			templatesDiv.removeChild(templatesDiv.childNodes[0]);
        }
		
		var templates = document.createElement('table');
		
		arg.forEach(function createComponentTemplateTableRow(args){
			console.log(args);
			console.log(args.id)
			var tr = document.createElement('tr');
					
			var name = document.createElement("INPUT");
			name.setAttribute("type", "text");
			name.setAttribute("value",args.name);
			name.setAttribute("id","subjectComponentNameInput_"+args.id);
					
			var number = document.createElement("INPUT");
			number.setAttribute("type", "number");
			number.setAttribute("value",args.number);
			number.setAttribute("id","subjectComponentTemplateNumberInput_"+args.id);
					
			var percentage = document.createElement("INPUT");
			percentage.setAttribute("type", "number");
 			percentage.setAttribute("value",args.markPercentage);
 			percentage.setAttribute("id","subjectComponentTemplatePercentageInput_"+args.id);
					
			var edit = document.createElement("INPUT");
			edit.setAttribute("type", "button");
			edit.setAttribute("value","Edit");
		    edit.setAttribute("onclick","addEditTemplate("+args.id+"); return false;");
		    
		    var remove = document.createElement("INPUT");
		    remove.setAttribute("type", "button");
		    remove.setAttribute("value","Delete");
		    remove.setAttribute("onclick","deleteComponentTemplate("+args.id+"); return false;");
			
	        tr.append(name);
	        tr.append(percentage);
	        tr.append(number);
	        tr.append(edit);
	        tr.append(remove);

	        templates.append(tr);
		});
		
		templatesDiv.append(templates);
	}
	
	

	
</script>

</html>