package Chat.Models.ViewModels;

import java.util.List;
import java.util.Set;

import Account.Models.User;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.InternalMessage;

public class GroupChatViewModel {

	private GroupChat groupChat;
	private List<InternalMessageViewModel> messages;
	private Set<User> users;

	public GroupChatViewModel(GroupChat groupChat, Set<User> users) {
		this(groupChat, users, null);
	}

	public GroupChatViewModel(GroupChat groupChat, Set<User> users, List<InternalMessageViewModel> messages) {
		this.groupChat = groupChat;
		this.users = users;
		this.messages = messages;
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

	public List<InternalMessageViewModel> getMessages() {
		return messages;
	}

	public void setMessages(List<InternalMessageViewModel> messages) {
		this.messages = messages;
	}

}
