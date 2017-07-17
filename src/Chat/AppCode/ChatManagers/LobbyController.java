package Chat.AppCode.ChatManagers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import Account.Models.User;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.InternalMessage;
import Chat.Models.DbModels.Lobby;

public class LobbyController {

	private List<GroupChatController> groupChatControllers;
	private Lobby lobby;

	public LobbyController(Lobby lobby) {
		groupChatControllers = new ArrayList<GroupChatController>();
		this.lobby = lobby;
	}

	public List<GroupChatController> getGroupChatControllers() {
		return groupChatControllers;
	}

	public void setGroupChatControllers(List<GroupChatController> groupChatControllers) {
		this.groupChatControllers = groupChatControllers;
	}

	public Lobby getLobby() {
		return lobby;
	}

	public List<InternalMessage> getMessagesByGroup(int groupId) {
		List<GroupChatController> filteredList = groupChatControllers.stream()
				.filter(x -> x.getGroupChat().getId() == groupId).collect(Collectors.toList());
		if(!filteredList.isEmpty())
			return filteredList.get(0).getMessages();
		return Collections.emptyList();
	}

	public void addGroupChat(GroupChat groupChat) {
		GroupChatController gcc = new GroupChatController(groupChat);
		groupChatControllers.add(gcc);
	}

	public void removeGroupChat(GroupChat groupChat) {
		List<GroupChatController> filteredList = groupChatControllers.stream()
				.filter(x -> x.getGroupChat().equals(groupChat)).collect(Collectors.toList());
		if (!filteredList.isEmpty())
			groupChatControllers.remove(filteredList.get(0));
	}

	public GroupChatController getGroupChatControllerById(int groupId) {
		List<GroupChatController> filteredList = groupChatControllers.stream()
				.filter(x -> x.getGroupChat().getId() == groupId).collect(Collectors.toList());
		if (filteredList.isEmpty())
			return null;
		return filteredList.get(0);
	}

	public List<GroupChat> getActiveGroupChats() {
		List<GroupChatController> filteredList = groupChatControllers.stream()
				.filter(x -> x.getUserEndpoints().size() != 0).collect(Collectors.toList());
		if (filteredList.isEmpty() || filteredList == null)
			return Collections.emptyList();
		return filteredList.stream().map(xx -> xx.getGroupChat()).collect(Collectors.toList());
	}

	public List<User> getActiveUsers(GroupChat groupChat){
		List<GroupChatController> filteredList = groupChatControllers.stream().filter(x -> x.getGroupChat().equals(groupChat)).collect(Collectors.toList());
		if(!filteredList.isEmpty())
			return filteredList.get(0).getActiveUsers();
		return Collections.emptyList();
	}
}
