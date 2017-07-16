package Chat.AppCode.ChatManagers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Chat.Models.DbModels.GroupChat;

public class LobbyController {

	private List<GroupChatController> groupChatControllers;

	public LobbyController() {
		setGroupChatControllers(new ArrayList<GroupChatController>());
	}

	public List<GroupChatController> getGroupChatControllers() {
		return groupChatControllers;
	}

	public void setGroupChatControllers(List<GroupChatController> groupChatControllers) {
		this.groupChatControllers = groupChatControllers;
	}

	public List<GroupChat> getActiveGroupChats() {
		List<GroupChatController> filteredList = groupChatControllers.stream()
				.filter(x -> x.getUserEndpoints().size() != 0).collect(Collectors.toList());
		return filteredList.stream().map(xx -> xx.getGroupChat()).collect(Collectors.toList());
	}

}
