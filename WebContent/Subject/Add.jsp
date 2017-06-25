<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<title>Add Subject</title>
</head>
<body>
	<h1 id="alert"></h1>
	<div>
		Name: <input type='text' name='name' id='name_id'><br />
		language: <input type='text' name='language' id='language_id'>
		<br /> Ects: <input type='number' name='ects' id='ects_id'><br />
		LecturerName: <input type='text' name='lecturerName'
			id='lecturerName_id'><br /> <input
			onclick="AddSubject(); return false;" type='button' value='Add'>
	</div>
	<div class="tooltip">Hover over me
	  <span class="tooltiptext">Add Subject First</span>
	</div>
	<div style="display:none">
		<input type="hidden" id='hidden_subject_id'>
	</div>
	<div>
		<div id="subjectComponents_id">
			<table>
			</table>
		</div>
		<div id="subjectComponentTemplateRow_id" style="display: none;">
			Subject Component Template: <input type="text" name="subjectComponentTemplateName" id="subjectComponentNameInput_id">
			<input type="number" name="subjectComponentTemplatePercentage"  id="subjectComponentTemplatePercentageInput_id">
			<input type ="number" name ="subjectComponentTemplateNumber" id="subjectComponentTemplateNumberInput_id">
			<input onclick="addTemplate(); return false;" type='button' value='Add Template'>
		</div>
	</div>
</body>
<script>

function AddSubject(){
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
			lecturerName: lecturerName	
		};
	
	$.ajax({
	    type: "POST",
	    url: "/AddSubjectServlet",
	    contentType: "application/json",
	    data: JSON.stringify(data),
	    success: function(response) {
	    	console.log(response);
	    	$("#hidden_subject_id").val(response);
	    	$("#subjectComponentTemplateRow_id").show();
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
	subjectId = $("#hidden_subject_id").val();
	
	data = {
		id: id,			
		name: componentName,
		percentage: componentPercentage,
		number: componentNumber,
		subjectId: subjectId,
	};

	$.ajax({
	    type: "POST",
	    url: "/EditComponentTemplateServlet",
	    contentType: "application/json",
	    data: JSON.stringify(data),
	    success: function(response) {
	    	//buildNewComponentTemplateTable(response);
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
		tr.setAttribute("id",args.id);
		
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
	    edit.setAttribute("onclick","EditTemplate("+args.id+"); return false;");
	    
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