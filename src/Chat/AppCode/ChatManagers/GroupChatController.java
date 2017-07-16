package Chat.AppCode.ChatManagers;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import Chat.AppCode.ChatCore.ChatEndpoint;
import Chat.Models.DbModels.GroupChat;

public class GroupChatController {

	private Map<Integer, ChatEndpoint> userEndpoints;
	private GroupChat groupChat;

	public GroupChatController(GroupChat groupChat) {
		userEndpoints = new ConcurrentHashMap<Integer, ChatEndpoint>();
		this.groupChat = groupChat;
	}

	public void addUser(int userId, ChatEndpoint chatEndpoint) {
		userEndpoints.put(userId, chatEndpoint);
	}
	
	public Set<ChatEndpoint> getUserEndpoints(){
		return userEndpoints.keySet().stream().map(x -> userEndpoints.get(x)).collect(Collectors.toSet());
	}
	
	public GroupChat getGroupChat(){
		return groupChat;
	}

}
