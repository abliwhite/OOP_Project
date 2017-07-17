package Chat.Models.ViewModels;

import java.util.List;

import Account.Models.User;
import Chat.Models.DbModels.GroupChat;

public class GroupChatViewModel {

	private GroupChat groupChat;
	private List<User> users;
	
	public GroupChatViewModel(GroupChat groupChat, List<User> users){
		this.groupChat = groupChat;
		this.users = users;
	}

	public GroupChat getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(GroupChat groupChat) {
		this.groupChat = groupChat;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
}
