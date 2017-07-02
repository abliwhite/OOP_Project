<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="../Subject/SubjectJS.js" ></script>
	
	<!-- <link rel="stylesheet" type="text/css" href="style.css"> -->
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	 		integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" 
			integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>Add Subject</title>
</head>
<body>
	<div class="container">
		Name: <input type='text' name='name' class="form-control" placeholder="Name" id='name_id'>
		language: <input type='text' name='language' class="form-control" placeholder="language" id='language_id'>
		Ects: <input type='number' name='ects' class="form-control" placeholder="Ects" id='ects_id'>
		LecturerName: <input type='text'  name='lecturerName' class="form-control" placeholder="LecturerName" id='lecturerName_id'>
		<input onclick="AddSubject(); return false;" id="subject_add_Button_id" type='button' class='btn btn-primary' value='Add'>
	</div>
	<div style="display:none" class="alert alert-success" id="alert_div_id" role="alert">
	</div>

	<div style="display:none">
		<input type="hidden" id='hidden_subject_id'>
	</div>
	<div>
		<div class="table-responsive" id="subjectComponents_id">
			
		</div>
		
		<div id="subjectComponentTemplateRow_id" style="display: none;">
			Add Subject Component: <input type="text" name="subjectComponentTemplateName" class="form-control" placeholder="Name" id="subjectComponentNameInput_id">
			<input type="number" name="subjectComponentTemplatePercentage" class="form-control" placeholder="Percent"  id="subjectComponentTemplatePercentageInput_id">
			<input type ="number" name ="subjectComponentTemplateNumber" class="form-control" placeholder="Number" id="subjectComponentTemplateNumberInput_id">
			<input onclick="addTemplate(); return false;" type='button' class='btn btn-primary' value='Add Template'>
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
	    	$("#subject_add_Button_id").hide();
	    	$("#subjectComponentTemplateRow_id").show();
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




</script>
</html>