package Chat.Models.ViewModels;

import java.util.List;
import java.util.Set;

import Account.Models.User;
import Chat.Models.DbModels.GroupChat;

public class GroupChatViewModel {

	private GroupChat groupChat;
	private Set<User> users;
	
	public GroupChatViewModel(GroupChat groupChat, Set<User> users){
		this.groupChat = groupChat;
		this.users = users;
	}

	public GroupChat getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(GroupChat groupChat) {
		this.groupChat = groupChat;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
}
