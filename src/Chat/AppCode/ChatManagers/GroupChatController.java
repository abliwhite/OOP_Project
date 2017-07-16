package Chat.AppCode.ChatManagers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Chat.AppCode.ChatCore.ChatEndpoint;

public class GroupChatController {

	private Map<Integer,ChatEndpoint> userEndPoints;
	
	public GroupChatController(){
		userEndPoints = new ConcurrentHashMap<Integer,ChatEndpoint>();
	}
	
	public void addUser(int userId,ChatEndpoint chatEndpoint){
		userEndPoints.put(userId, chatEndpoint);
	}
	
}
