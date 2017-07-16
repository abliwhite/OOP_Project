<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@page import="Database.DbCertificate"%>
<%@ page import="Subject.Models.DbModels.*"%>
<%@ page import="Subject.Models.ViewModels.*"%>
<%@ page import="Common.Models.*"%>
<%@ page import="java.util.List"%>  
<%@ page import="java.util.ArrayList"%> 
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="../Subject/SubjectShareJS.js" ></script>
	<script type="text/javascript" src="../shareJS.js" ></script>
	
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
<%
	ResponseModel<Object, SubjectTemplateListsViewModel> responseModel = (ResponseModel)request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);
    
	List<SubjectTerm> subjectTermsList = responseModel.getResultObject().getsTerms();
	List<SubjectComponentType> scNames = responseModel.getResultObject().getScNames();
%>
</head>
<body>
	<form id="display_subject_required_info" style="display: none;">
		<div class="form-group row">
		    <label id="name_label_id" class="col-sm-2 col-form-label"></label>
		    <label id="year_label_id" class="col-sm-2 col-form-label"></label>
		    <label id="term_label_id" class="col-sm-2 col-form-label"></label>
		</div>
	</form>
	<div class="container">
		<div id = "subject_required_info">
			Name: <input type='text' name='name' class="form-control" placeholder="Name" id='name_id'>
			Year: <input type='number' name='language' class="form-control" placeholder="Year" id='year_id'>
			Term: <select class="form-control" name="SubjectTerm" id="term_id">
			<%
				for(int i=0; i < subjectTermsList.size(); i++)
				{
					SubjectTerm term = subjectTermsList.get(i);
					out.print("<option value = '" + term.getId() + "'>" + term.getName() + "</option>");
				}
			%>
			</select>
		</div>
		<div id = "subject_optional_info">
			Ects: <input type='number' name='ects' class="form-control" placeholder="Ects" id='ects_id'>
			Language: <input type='text' name='language' class="form-control" placeholder="Language" id='language_id'>
			Lecturer Name: <input type='text' name='lecturerName' class="form-control" placeholder="Lecturer Name" id='lecturerName_id'>
		</div>
		
		<input onclick="AddSubject(); return false;" id="subject_add_Button_id" type='button' class='btn btn-primary' value='Add'>
	</div>
	<div style="display:none" class="alert alert-success" id="subject_alert_div_id" role="alert">
	</div>

	<div style="display:none">
		<input type="hidden" id='hidden_subject_id'>
	</div>
	<div>
		<div class="table-responsive" id="subjectComponents_id">
			
		</div>
		<input id='show_Add_Subject_Template_id' type='button' onclick="showAddSubjectTemplate();" class='btn btn-primary' value="Add Component" style="display: none;">
		<div id="subjectComponentTemplateRow_id" style="display: none;">
			Add Subject Component: 
			<select class="form-control" name="SubjectTerm" id="subjectComponentNameSelect_id">
			<%
				for(int i=0; i < scNames.size(); i++)
				{
					
					out.print("<option value='"+scNames.get(i).getId()+"'>" + scNames.get(i).getName() + "</option>");
				}
			%>
			</select>
			<input type="number" name="subjectComponentTemplatePercentage" class="form-control" placeholder="Percent"  id="subjectComponentTemplatePercentageInput_id">
			<input type ="number" name ="subjectComponentTemplateNumber" class="form-control" placeholder="Number" id="subjectComponentTemplateNumberInput_id">
			<input onclick="addTemplate(); return false;" type='button' class='btn btn-primary' value='Save'>
		</div>
		
	</div>
	<div style="display:none" class="alert alert-success" id="subject_component_alert_div_id" role="alert">
	</div>
</body>
<script>





function AddSubject(){
	name = $('#name_id').val();
	year = $('#year_id').val();
	termId = $('#term_id').val();
	term = $('#term_id').find(":selected").text();
	language = $('#language_id').val();
	ects = $('#ects_id').val();
	lecturerName = $('#lecturerName_id').val();
	
	if(name == "" || language == "" || ects == "" || lecturerName == "" || year==""){
		$("#subject_alert_div_id").removeClass("alert alert-success");
		$("#subject_alert_div_id").addClass("alert alert-danger");
		$("#subject_alert_div_id").html("Fill All Fields!");
    	$("#subject_alert_div_id").show();
    	
    	fadeAlertMessage("subject_alert_div_id");
		return;
	}
	
	data = {
			name: name,
			year: year,
			termId: termId,
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
	    	
	    	if(response.isSuccess == false){
	    		$("#subject_alert_div_id").removeClass("alert alert-success");
	    		$("#subject_alert_div_id").addClass("alert alert-danger");
	    		$("#subject_alert_div_id").html(response.resultMessage);
	    		$("#subject_alert_div_id").show();
	    		
	    		fadeAlertMessage("subject_alert_div_id");
	    		return;
	    	}
	    	
	    	$("#hidden_subject_id").val(response.resultObject);	
	    	
	    	$("#subject_add_Button_id").hide();
	    	$("#subject_optional_info").hide();
	    	$("#subject_required_info").hide();
	    	
	    	$("#name_label_id").text(name);
	    	$("#year_label_id").text(year);
	    	$("#term_label_id").text(term);
	    	$("#display_subject_required_info").show();
	    	$("#show_Add_Subject_Template_id").show();
	    	
	    	$("#subject_alert_div_id").removeClass("alert alert-danger");
	    	$("#subject_alert_div_id").addClass("alert alert-success");
	    	$("#subject_alert_div_id").html(response.resultMessage);
	    	$("#subject_alert_div_id").show();
	    	
	    	fadeAlertMessage("subject_alert_div_id");
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