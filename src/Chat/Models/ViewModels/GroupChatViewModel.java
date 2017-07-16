package Chat.Models.ViewModels;

import java.util.List;

import Account.Models.User;
import Chat.Models.DbModels.GroupChat;

public class GroupChatViewModel {

	private GroupChat groupChat;
	private List<User> users;
	
	public GroupChatViewModel(GroupChat groupChat, List<User> users){
		this.setGroupChat(groupChat);
		this.setUser(users);
	}

	public GroupChat getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(GroupChat groupChat) {
		this.groupChat = groupChat;
	}

	public List<User> getUser() {
		return users;
	}

	public void setUser(List<User> users) {
		this.users = users;
	}
	
	
	
}
