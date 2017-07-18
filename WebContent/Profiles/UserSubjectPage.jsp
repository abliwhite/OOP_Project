<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		</div>
	</form>
	
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
					out.println("<input type='button' class='btn btn-info' value='materials' onclick='materials("+componentId+");' >");
					out.println("<input type='button' class='btn btn-success' value='Lobby' onclick='lobby("+componentId+");' >");
					out.println("</td>");
					out.println("</tr>");
				}
				%>
			</table>
		</div>
		
	</div>	
	<div style="display:none" class="alert alert-success" id="subject_component_alert_div_id" role="alert">
	</div>

</body>

<script>
	
function lobby(commonSubjectId){
	redirectUrl = "LobbyPageGeneratorServlet?id="+commonSubjectId;
	window.location = redirectUrl;
}
	
</script>
</html>