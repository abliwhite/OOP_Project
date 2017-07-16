<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Common.Models.*"%>
<%@ page import="Account.Models.*"%>
<%@ page import="Subject.Models.ViewModels.*"%>
<%@ page import="Chat.Models.ViewModels.*"%>
<%@ page import="Chat.Models.DbModels.*"%>
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
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lobby</title>
	<%
		ResponseModel<Object, LobbyViewModel> responseModel = (ResponseModel)request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);
	    
		LobbyViewModel lobbyViewModel = responseModel.getResultObject();
		
		List<GroupChatViewModel> activeChats = lobbyViewModel.getActiveGroupchats();
		List<GroupChat> userChats = lobbyViewModel.getArchivedGroupChats();
		CommonSubjectComponentViewModel cscViewModel = lobbyViewModel.getCscViewModel();
		Lobby lobby = lobbyViewModel.getLobby();
		
	%>
	
</head>
<body>
	

	<div class="row">
		<%
			for(int i = 0;i< activeChats.size();i++){
				out.print("<div class='card w-50'>");
				out.print("<div class='card-header'>"+activeChats.get(i).getGroupChat().getName()+"</div>");
				
				out.print("<div class='card-card-block'>");
				out.print("<h4 class='card-title'>Users</h4>");
				
				List<User> users = activeChats.get(i).getUsers();
				String u = "";
				
				for(int j=0; j < users.size()-1;j++){
					u = u + users.get(j).getUsername()+", ";
				}
				u = u + users.get(users.size()-1).getUsername();
				out.print("<p class='card-text'>"+u+"</p>");
				out.print("<input type='button' value='Join' class='btn btn-primary' onclick='askToJoin()' >");
				out.print("</div>");
				out.print("</div>");
			}
		
		%>

	

</body>

<script>

function askToJoin(){
	
}

</script>

</html>