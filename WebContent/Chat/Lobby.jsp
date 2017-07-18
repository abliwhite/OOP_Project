<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Common.Models.*"%>
<%@ page import="Account.Models.*"%>
<%@ page import="Subject.Models.ViewModels.*"%>
<%@ page import="Chat.Models.ViewModels.*"%>
<%@ page import="Chat.Models.DbModels.*"%>
<%@ page import="java.util.List"%> 
<%@ page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="../Subject/SubjectShareJS.js" ></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	 		integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" 
			integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lobby</title>
	<%
		HttpSession ses = request.getSession();
		User us = (User)ses.getAttribute(ses.getId());
	
		ResponseModel<Object, LobbyViewModel> responseModel = (ResponseModel)request.getAttribute(ResponseModel.RESPONSE_MESSAGE_ATTRIBUTE);
	    
		LobbyViewModel lobbyViewModel = responseModel.getResultObject();
		
		List<GroupChatViewModel> activeChats = lobbyViewModel.getActiveGroupchats();
		List<GroupChat> userChats = lobbyViewModel.getArchivedGroupChats();
		CommonSubjectComponentViewModel cscViewModel = lobbyViewModel.getCscViewModel();
		Lobby lobby = lobbyViewModel.getLobby();
		
	%>
	
<style type="text/css">//nawili nasesxebia 
		
		.card-group {
	  display: -webkit-flex;
	  display: flex;
	  flex-wrap: wrap;
	  /* max-height:475px; <-- remove */
	  background-color: lightgrey;
	}
	
	.card img {
	  width: 100%;
	}
	
	.card {
	  background-color: cornflowerblue;
	  width: 30%;
	  margin: 0px;
	  flex: 2;
	  border: 1px solid lightgrey;
	  display: flex;           /* new */
	  flex-direction: column;  /* new */
	}
	
	.card-block {
	  padding: 10px;
	  background-color: #fff;
	  flex: 1;                /* new */
	}
	
	.card-title {
	  font-size: 18px;
	  color: grey;
	  font-family: verdana, sans;
	}
	
	.card-footer {
	  padding: 15px;
	  border-top: 1px solid lightgrey;
	  background-color: lightgrey;
	}
	.centerAddPlus {
		margin: 0;
	    position: absolute;
	    top: 40%;
	    left: 33%;
	}
	
	.centerJoinButton {
		margin: 0;
	    position: absolute;
	    top: 77%;
	    left: 33%;
	}
	
#container {
  display: block;
  min-width: 900px;
  max-width: 900px;
  position: relative;
  border: solid 1px green;
}
.groupRow {
	display: inline-block;
	position: relative;
	min-width: 180px;
	max-width: 180px;
	min-height: 220px;
	margin: 3px;
}

.mid{
	position: relative;
}

.addGroupChat{
	min-width: 143px;
	max-width: 143px;
	max-height: 162px;
	min-height: 162px;
}	
	
</style>
	
</head>
<body>
	
	
	<div class="col-md-8 container">
		
		<%
			out.print("<input type='hidden' value='"+session.getId()+"' id='si_id'>");
			out.print("<input type='hidden' value='"+us.getUsername()+"' id='username_id'>");
		%>
			<input type='hidden' id="current_groupChat_id">
		<div>
			
			<div class='form-group col-lg-3 container'>
				<h3><span class="badge badge-primary">Active Group Chats</span></h3>
			</div>
			
			<div id="active_group_chats" class='row'>
				<%
					for(GroupChatViewModel item : activeChats){
						out.print("<div id='groupChar_"+ item.getGroupChat().getId()+"' class='card w-25 groupRow'>");
						out.print("<div class='card-header'>"+ item.getGroupChat().getName()+ "</div>");
						out.print("<div class='card-card-block' style='height:100px'>");
						out.print("<h4 class='card-title'>Users</h4>");
						String users = "";
						for(User user:item.getUsers()){
							users = users + user.getUsername();
						}
						out.print("<p class='card-text'>"+users+"</p>");
						out.print("<input type='button' value='Join' class='btn btn-primary' onclick='askToJoin("+item.getGroupChat().getId()+")' >");
						out.print("</div>");
						out.print("</div>");
					}
				
				%>
				
				
				<div class='card w-25 groupRow'>	
					<div class='card-header'> Create Group </div>
						<a class='btn btn-primary'>
						<div class='card-card-block addGroupChat' data-toggle="modal" data-target="#myModal" >
							<i class="fa fa-plus fa-5x centerAddPlus"></i>
						</div>
					</a>
				</div>
			</div>
		</div>
		<div id="chat_id" style="display: none;">
			<table>
				<tr>
			        <td>
			            <textarea rows="10" cols="80" id="chat_messages" title="Chat"></textarea>
			        </td>
			    </tr>
			    <tr>
			        <td>
				        <input type="text" size="51" id="msg" placeholder="Message"/>
			            <button type="button" onclick="send();">Send</button>
			        </td>
			    </tr>
			</table>
		</div>
	</div>
	


	<!-- Modal avige bootstrap is modeli rogorc unda gaaketo popup-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Create Group</h4>
	      </div>
	      <div class="modal-body">
	        	Group Name: <input type="text" class="form-control " placeholder="Group Name" name="groupname" id="groupname" >
	        	<div class="form-check form-check-inline">
				  <label class="form-check-label">
				    <input class="form-check-input" type="radio" name="groupType" id="inlineRadio1" value="0"> Public
				  </label>
				</div>
				<div class="form-check form-check-inline">
				  <label class="form-check-label">
				    <input class="form-check-input" type="radio" name="groupType" id="inlineRadio2" value="1"> Private
				  </label>
				</div>
				<div class="form-check form-check-inline">
				  <label class="form-check-label">
				    <input class="form-check-input" type="radio" name="groupType" id="inlineRadio3" value="2"> Secret
				  </label>
				</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="newGroupChat()">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	
</body>

<script>
var ws;
window.onload = function() {
	_sI = $("#si_id").val();
    ws = new WebSocket("ws://" + document.location.host  + "/LobbyPageGeneratorServlet/chat/" + _sI);
    console.log(ws);

    ws.onmessage = function (event) {
        var log = document.getElementById("chat_messages");
        console.log(event.data);
        console.log("msg");
        var message = JSON.parse(event.data);
        console.log(message.type);
        console.log(message.content);
       if(message.type == "ResponseMessage" && message.content == "Success"){
    	   console.log("responseeeeeeeeeeeeeeeeeeeeeeeee");
    	   refreshLobbyData();
    	   refreshChatWindow(message.senderGroupId);
    	   
    	   $("#current_groupChat_id").val(message.senderGroupId);
    	   console.log(message.content);
       }
       else{
    	   console.log("araresponseeeeeee");
       }
       
       if(message.type == "RequestMessage"){
    	   console.log(message.content);
       }
       
       if(message.type == "InternalMessage"){
    	   refreshChatWindow($("#current_groupChat_id").val());
       }
       
    };
    
};


$('#myModal').on('shown.bs.modal', function () {
	  $('#myInput').focus()
	})
	
function send(){
	groupChatId = $("#current_groupChat_id").val();
	var json = JSON.stringify({
        userId: _userId,
        type: "InternalMessage",
        receiverId: groupChatId,
        lobbyId: _lobbyId,
        content: $("#msg").val()
    });
	
    ws.send(json);
}
	
function fillChatWithMessages(json){
	chat = $("#chat_messages");
	chat.empty();
	messages = "";
	u = $("#username_id").val();
	for (i = 0; i < json.messages.length; i++) {
		user = json.messages[i].user.username
		if(user==u){
			messages += "Me: "+json.messages[i].message.message+"\n";		
		}else{
	    	messages += user+": "+json.messages[i].message.message+"\n";
		}
	}
	chat.html(messages);
}

function refreshChatWindow(groupChatId){
	data = {
			lobbyId: _lobbyId,
			groupId : groupChatId
		};
	
	$.ajax({
	    type: "POST",
	    url: "/RefreshChatServlet",
	    contentType: "application/json",
	    data: JSON.stringify(data),
	    success:  function(data) {
	    	$("#chat_id").show();
	    	fillChatWithMessages(data.resultObject);
	    	//refreshLobbyData();
		}
	});
}

function askToJoin(groupChatId){
	//$("#chat_id").show();
	
		var json = JSON.stringify({
	        userId: _userId,
	        type: "RequestMessage",
	        receiverId: groupChatId,
	        lobbyId: _lobbyId
	    });
		$("#current_groupChat_id").val(groupChatId);
		
		ws.send(json);
	
}

function drawActiveGroups(activeGroups){
	$("#active_group_chats").empty();
	console.log(activeGroups);
	result = "";
	activeGroups.forEach(function(entry) {
		result = result + entry;
	});
	$("#active_group_chats").html(result);
}

_lobbyId =<%out.print(lobby.getId());%>
_userId = <%out.print(us.getId());%>
_componentId = <%out.print(""+lobby.getSubjectComponentID());%>
function refreshLobbyData(){
	
	data = {
			lobbyId: _lobbyId,
			componentId : _componentId
		};
	
	$.ajax({
	    type: "POST",
	    url: "/RefreshLobbyPageServlet",
	    contentType: "application/json",
	    data: JSON.stringify(data),
	    success:  function( data ) {
		  var activeGroups = [];
		  var passiveGroups = [];
		  console.log(data.resultObject.activeGroupchats);
		  data.resultObject.activeGroupchats.forEach(function(activeChat) {
			  activeGroups.push(getActiveGroup(activeChat));
		  });
		  console.log(getAddNewChatCard());
		  activeGroups.push(getAddNewChatCard());
		  /*
		  $.each( data.resultObject.userGroupChats, function(passiveChat) {
			  passiveGroups.push(getPassiveGroup(activeChat));
		  });
		  */
		  drawActiveGroups(activeGroups);
		  //drawPassiveGroups(passiveGroups);
		 
		  /* $( "<ul/>", {
		    "class": "my-new-list",
		    html: items.join( "" )
		  }).appendTo( "body" );*/
		}
	});
}
refreshLobbyData();

function newGroupChat(){

	rdBntVal = $('input[name=groupType]:checked').val();
	groupName = $('input[name=groupname]').val();
	
	console.log(rdBntVal);
	console.log(groupName);
	
	
	data = {
			lobbyId: _lobbyId,
			groupChatName : groupName,
			privacyStatusId : 1 //(parseInt(rdBntVal) + 1)
		};
	
	$.ajax({
	    type: "POST",
	    url: "/AddGroupChatServlet",
	    contentType: "application/json",
	    data: JSON.stringify(data),
	    success:  function( data ) {
	    	if(data.isSuccess){
	    		refreshLobbyData();
		    	refreshChatWindow(data.resultObject.id);
		    	$("#current_groupChat_id").val(data.resultObject.id);
	    	}
	    	
		}
	});

	$('#myModal').modal('toggle');
}

function getAddNewChatCard()
{
	activeg = "<div class='card w-25 groupRow'>";
	activeg += "<div class='card-header'> Create Group </div>";
	activeg +="<a class='btn btn-primary'>";
	activeg +="<div class='card-card-block addGroupChat' data-toggle='modal' data-target='#myModal' >";
	activeg +="<i class='fa fa-plus fa-5x centerAddPlus'></i>";
	activeg +="</div>";
	activeg +="</a>";
	activeg +="</div>";
	
	return activeg;
}

function getActiveGroup(json)
{
	console.log(json);
	activeg = "<div class='card w-25 groupRow'>";
	console.log(json.groupChat);
	console.log(json.groupChat.name);
	activeg += "<div class='card-header'>" + json.groupChat.name + "</div>";
	activeg +="<div class='card-card-block' style='height:100px'>";
	activeg +="<h4 class='card-title'>Users:</h4>";
	text = "";
	for (i = 0; i < json.users.length-1; i++) { 
	    text += json.users[i].username + ",";
	}
	text += json.users[json.users.length-1].username;
	activeg +="<p class='card-text'>"+text+"</p>";
	activeg +="<input type='button' value='Join' class='btn btn-primary centerJoinButton' onclick='askToJoin("+json.groupChat.id+")' >";
	activeg +="</div>";
	activeg +="</div>";
	
	return activeg;
}

function getPassiveGroup(json)
{
	activeg = "<div class='card w-25'>";
	activeg += "<div class='card-header'> Create Group </div>";
	activeg += "<div class='card-card-block'>";
	activeg += "<h4 class='card-title'>Users</h4>";
	activeg += "<p class='card-text'>"+u+"</p>";
	activeg += "<input type='button' value='Join()' class='btn btn-primary' onclick='askToJoin()' >";
	activeg += "</div>";
	activeg += "</div>";
}
</script>

</html>