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
CommonSubjectComponentViewModel viewModel = (CommonSubjectComponentViewModel)resp.getResultObject();
	List<SubjectComponentMaterial> materials = viewModel.getMaterials();
	Subject subject=viewModel.getSubject();
	
%>
</head>



<body>
<div class="table-responsive" id="subjectComponents_id">
			<table class='table'>
				<thead>
					<tr>
						<th>
							Path
						</th>						
						<th>
							UploadDate
						</th>
					</tr>
				</thead>
				<% 
				List<SubjectComponentMaterial> temp = viewModel.getMaterials();
				for(int i = 0;i < temp.size();i++)
				{
					String path = temp.get(i).getMaterialPath();
					String uploadTime = temp.get(i).getUploadDate();
					int materialId=temp.get(i).getId();
					out.println("<tr id = '"+materialId+"' >");
					out.println("<td>");
					out.println("<label id='subjectComponentNameInput_"+materialId+"' >"+path+"</label>");
					out.println("</td>");
					out.println("<td>");
					out.println("<label id='subjectComponentTemplateNumberInput_"+materialId+"' >"+uploadTime+"</label>");
					out.println("</td>");
					out.println("<td>");
					//out.println("<input type='button' class='btn btn-warning' value='Edit' onclick='EditTemplate("+componentId+");' >");
					out.println("<input type='button' class='btn btn-danger' value='Delete' onclick='deleteComponentTemplate("+materialId+");' >");
					out.println("<input type='button' class='btn btn-info' value='materials' onclick='materials("+materialId+");' >");
					out.println("</td>");
					out.println("</tr>");
				}
				%>
			</table>
			<form action="MaterialUploadervlet" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" class="btn btn-success" />
</form>
		</div>
		

</body>
</html>