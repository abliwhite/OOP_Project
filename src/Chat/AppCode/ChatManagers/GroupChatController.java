package Chat.AppCode.ChatManagers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import Account.Models.User;
import Chat.AppCode.ChatCore.ChatEndpoint;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.InternalMessage;

public class GroupChatController {

	private Map<User, ChatEndpoint> userEndpoints;
	private Set<User> users;
	private List<InternalMessage> messages;
	private GroupChat groupChat;

	public GroupChatController(GroupChat groupChat) {
		userEndpoints = new ConcurrentHashMap<User, ChatEndpoint>();
		users = new CopyOnWriteArraySet<User>();
		messages = new CopyOnWriteArrayList<InternalMessage>();
		this.groupChat = groupChat;
	}

	public void addUser(User user, ChatEndpoint chatEndpoint) {
		userEndpoints.put(user, chatEndpoint);
		users.add(user);
	}
	
	public void removeUser(User user){
		userEndpoints.remove(user);
	}

	public Set<ChatEndpoint> getUserEndpoints() {
		Set<ChatEndpoint> cep = userEndpoints.keySet().stream().map(x -> userEndpoints.get(x)).collect(Collectors.toSet());
		if(cep!=null && !cep.isEmpty())
			return cep;
		return Collections.emptySet();
	}

	public Set<User> getUsers() {
		return users;
	}
	
	public List<User> getActiveUsers(){
		if(userEndpoints.isEmpty()) return Collections.emptyList();
		return userEndpoints.keySet().stream().collect(Collectors.toList());
	}

	public GroupChat getGroupChat() {
		return groupChat;
	}

	public List<InternalMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<InternalMessage> messages) {
		this.messages = messages;
	}

	public void addMessage(InternalMessage message) {
		messages.add(message);
		
	}

}
