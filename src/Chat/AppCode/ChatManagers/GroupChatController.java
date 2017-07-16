package Chat.AppCode.ChatManagers;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import Account.Models.User;
import Chat.AppCode.ChatCore.ChatEndpoint;
import Chat.Models.DbModels.GroupChat;

public class GroupChatController {

	private Map<Integer, ChatEndpoint> userEndpoints;
	private Set<User> users;
	private GroupChat groupChat;

	public GroupChatController(GroupChat groupChat) {
		userEndpoints = new ConcurrentHashMap<Integer, ChatEndpoint>();
		users = new CopyOnWriteArraySet<User>();
		this.groupChat = groupChat;
	}

	public void addUser(int userId, ChatEndpoint chatEndpoint) {
		userEndpoints.put(userId, chatEndpoint);
	}
	
	public Set<ChatEndpoint> getUserEndpoints(){
		return userEndpoints.keySet().stream().map(x -> userEndpoints.get(x)).collect(Collectors.toSet());
	}
	
	public Set<User> getUsers(){
		return users;
	}
	
	public GroupChat getGroupChat(){
		return groupChat;
	}

}
