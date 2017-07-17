<%@page import="java.util.stream.Collector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="Subject.Models.ViewModels.SubjectViewModel"%>
<%@ page import="Subject.Models.ViewModels.*"%>
<%@ page import="Subject.Models.DbModels.Subject"%>
<%@ page import="java.util.List"%>  
<%@ page import="Common.Models.*" %>  
<%@ page import="Subject.Models.DbModels.*" %>
<%@ page import="java.util.stream.Collectors"%> 


   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="../Subject/SubjectShareJS.js" ></script>
	<script type="text/javascript" src="../shareJS.js" ></script>
	
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
	ResponseModel resp = (ResponseModel)request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);
	SubjectViewModel viewModel = (SubjectViewModel)resp.getResultObject();
	SubjectTemplateListsViewModel stv = viewModel.getSubjectTemplateListsViewModel();
	Subject subject = viewModel.getSubject();
	SubjectInfo subjectInfo = viewModel.getInfo();
	SubjectTerm subjectTerm = viewModel.getTerm();
	
	List<SubjectComponentType> scNames = stv.getScNames();
	List<SubjectTerm> subjectTermsList = stv.getsTerms();
%>

<body>
	<form id="display_subject_required_info">
		<div class="form-group row">
		  <span class="label label-default">  <label id="name_label_id" class="col-sm-2 col-form-label"><%=subject.getName() %></label> </span>
		   <span class="label label-default">  <label id="year_label_id" class="col-sm-2 col-form-label"><%=subject.getYear() %></label></span>
		   <span class="label label-default">  <label id="term_label_id" class="col-sm-2 col-form-label"><%=subjectTerm.getName() %></label></span>
			<input id='show_edit_Subject_id' type='button' onclick="showEditSubject();" class='btn btn-success' value="Edit">
		</div>
	</form>
	<div class="container">
		<div id = "subject_required_info" style="display:none">
			Name: <input value='<%=subject.getName() %>' type='text' name='name' class="form-control" placeholder="Name" id='name_id'>
			Year: <input value='<%=subject.getYear() %>' type='number' name='language' class="form-control" placeholder="Year" id='year_id'>
			Term: <select class="form-control" name="SubjectTerm" id="term_id">
			<%
				for(int i=0; i < subjectTermsList.size(); i++)
				{
					SubjectTerm term = subjectTermsList.get(i);
					if(term.getId() == subjectTerm.getId()){
						out.print("<option value = '" + term.getId() + "' selected = 'selected'>" + term.getName() + "</option>");
					}else{
						out.print("<option value = '" + term.getId() + "'>" + term.getName() + "</option>");
					}
				}
			%>
			</select>
		</div>
		<div style="display:none">
			<input value='<%=subjectInfo.getId() %>' type="hidden" id='hidden_subjectInfo_id'>
		</div>
		<div id = "subject_optional_info" style="display:none">
			Ects: <input value='<%=subjectInfo.getEcts() %>' type='number' name='ects' class="form-control" placeholder="Ects" id='ects_id'>
			Language: <input value='<%=subjectInfo.getLanguage() %>' type='text' name='language' class="form-control" placeholder="Language" id='language_id'>
			Lecturer Name: <input value='<%=subjectInfo.getLecturerName() %>' type='text' name='lecturerName' class="form-control" placeholder="Lecturer Name" id='lecturerName_id'>
		</div>
		<br>
		<input style="display:none" onclick="EditSubject(<%=subject.getId()%>); return false;" id="subject_edit_Button_id" type='button' class='btn btn-primary' value='Save'>
	</div>
	<div style="display:none" class="alert alert-success" id="subject_alert_div_id" role="alert">
	</div>
	<div style="display:none">
		<input type="hidden" value='<%=subject.getId() %>' id='hidden_subject_id'>
	</div>
	<div>
		<div class="table-responsive" id="subjectComponents_id">
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
				List<CommonSubjectComponentViewModel> temp = viewModel.getCommonSubjectComponentViewModels();
				for(int i = 0;i < temp.size();i++)
				{
					int componentId = temp.get(i).getId();
					String componentName = temp.get(i).getSubjectComponentType().getName();
					int componentNumber = temp.get(i).getNumber();
					double componentPercentage = temp.get(i).getMarkPercentage();
					
					out.println("<tr id = '"+componentId+"' >");
					out.println("<td>");
					out.println("<label id='subjectComponentNameInput_"+componentId+"' >"+componentName+"</label>");
					out.println("</td>");
					out.println("<td>");
					out.println("<label id='subjectComponentTemplateNumberInput_"+componentId+"' >"+componentNumber+"</label>");
					out.println("</td>");
					out.println("<td>");
					out.println("<label id='subjectComponentTemplatePercentageInput_"+componentId+"' >" +componentPercentage+"</label>");
					out.println("</td>");
					out.println("<td>");
					//out.println("<input type='button' class='btn btn-warning' value='Edit' onclick='EditTemplate("+componentId+");' >");
					out.println("<input type='button' class='btn btn-danger' value='Delete' onclick='deleteComponentTemplate("+componentId+");' >");
					out.println("<input type='button' class='btn btn-info' value='materials' onclick='materials("+componentId+");' >");
					out.println("</td>");
					out.println("</tr>");
				}
				%>
			</table>
		</div>
		<input id='show_Add_Subject_Template_id' type='button' onclick="showAddSubjectTemplate();" class='btn btn-primary' value="Add Component" >
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
			<div class="form-group">
			<br>
			<input type="number" name="subjectComponentTemplatePercentage" class="form-control" placeholder="Percent"  id="subjectComponentTemplatePercentageInput_id">
			</div>
			<div class="form-group">
			<input type ="number" name ="subjectComponentTemplateNumber" class="form-control" placeholder="Number" id="subjectComponentTemplateNumberInput_id">
			</div>
			<div class="form-group">
			<input onclick="addTemplate(); return false;" type='button' class='btn btn-primary' value='Save'>
			</div>
		</div>
	</div>	
	<div style="display:none" class="alert alert-success" id="subject_component_alert_div_id" role="alert">
	</div>

</body>

<script>

	function showEditSubject(){
		$('#subject_required_info').show();
		$('#subject_optional_info').show();
		$('#subject_edit_Button_id').show();
		$('#display_subject_required_info').hide();
		$('#show_edit_Subject_id').hide();
	}

	function EditSubject(id){
		name = $('#name_id').val();
		year = $('#year_id').val();
		termId = $('#term_id').val();
		term = $('#term_id').find(":selected").text();
		language = $('#language_id').val();
		ects = $('#ects_id').val();
		lecturerName = $('#lecturerName_id').val();
		subjectInfoId = $('#hidden_subjectInfo_id').val();
		subjectId = id;
		
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
				lecturerName: lecturerName,
				subjectId: id,
				subjectInfoId: subjectInfoId
			};
	
		
		
		$.ajax({
		    type: "POST",
		    url: "/EditSubjectServlet",
		    contentType: "application/json",
		    data: JSON.stringify(data),
		    success: function(response) {
		    	if(response.isSuccess == false){
		    		$("#subject_alert_div_id").removeClass("alert alert-success");
		    		$("#subject_alert_div_id").addClass("alert alert-danger");
		    		$("#subject_alert_div_id").html(response.resultMessage);
		    		
		    		fadeAlertMessage("subject_alert_div_id");
		    		return;
		    	}
		    	$('#subject_required_info').hide();
				$('#subject_optional_info').hide();
				$('#subject_edit_Button_id').hide();
				
				$("#name_label_id").html(name);
				$("#year_label_id").html(year);
				$("#term_label_id").html(term);
				
				$('#display_subject_required_info').show();
				$('#show_edit_Subject_id').show();
				
				
				
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