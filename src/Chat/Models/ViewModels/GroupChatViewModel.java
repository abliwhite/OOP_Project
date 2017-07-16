package Chat.Models.ViewModels;

import Account.Models.User;
import Chat.Models.DbModels.GroupChat;

public class GroupChatViewModel {

	private GroupChat groupChat;
	private User user;
	
	public GroupChatViewModel(GroupChat groupChat, User user){
		this.setGroupChat(groupChat);
		this.setUser(user);
	}

	public GroupChat getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(GroupChat groupChat) {
		this.groupChat = groupChat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
