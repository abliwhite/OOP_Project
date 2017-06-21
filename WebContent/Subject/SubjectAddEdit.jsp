<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Common.AppCode.CommonConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><%=(String) request.getAttribute(CommonConstants.ADD_EDIT_PAGE_TITLE_ATTRIBUTE)%></title>
</head>

<body>
	<h1 id="alert"></h1>
	<div>
		Name: <input type="text" name="name" id="name_id" >
		<br />
		language: <input type="text" name="language" id="language_id" >
		<br />
		Ects: <input type="text" name="ects" id="ects_id" > 
		<br />
		LecturerName: <input type="text" name="lecturerName" id="lecturerName_id" > 
		<br />
		<input onclick="addEditSubject(); return false;" type='button' value='Save'>
	</div>
	<div>
		<div id = "subjectComponents_id">
			
		</div>
		<div id="subjectComponentTemplateRow_id">
			Subject Component Template: <input type="text" name="subjectComponentTemplateName" id="subjectComponentNameInput_id">
			<input type="number" name="subjectComponentTemplatePercentage"  id="subjectComponentTemplatePercentageInput_id">
			<input type ="number" name ="subjectComponentTemplateNumber" id="subjectComponentTemplateNumberInput_id">
			<input onclick="addEditTemplate(); return false;" type='button' value='Add Template'>
		</div>
	</div>	
		

</body>

<script>
	function addEditTemplate(id) {
		var componentName;
		var componentPercentage;
		var componentNumber;
		if(id == null){
			componentName = $("#subjectComponentNameInput_id").val();
			componentPercentage = $("#subjectComponentTemplatePercentageInput_id").val();
			componentNumber = $("#subjectComponentTemplateNumberInput_id").val();
			
			if(componentName == "" || componentPercentage == "" || componentNumber == ""){
				$("#alert").innerHTML = "Fill All Fields";
				return;
			}
		}else{
			componentName = $("#subjectComponentNameInput_"+id).val();
			componentPercentage = $("#subjectComponentTemplatePercentageInput_"+id).val();
			componentNumber = $("#subjectComponentTemplateNumberInput_"+id).val();
		}
		
			
		
		data = {
			id: id,			
			name: componentName,
			percentage: componentPercentage,
			number: componentNumber	
		};
	
		$.ajax({
		    type: "POST",
		    url: "/ComponentTemplateAddEditServlet",
		    contentType: "application/json",
		    data: JSON.stringify(data),
		    success: function(response) {
		    	buildNewComponentTemplateTable(response);
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
			edit.setAttribute("value","Edit Template");
		    edit.setAttribute("onclick","addEditTemplate("+args.id+"); return false;");
			
	        tr.append(name);
	        tr.append(percentage);
	        tr.append(number);
	        tr.append(edit);

	        templates.append(tr);
		});
		
		templatesDiv.append(templates);
	}
	
	

	
</script>

</html>