package Common.AppCode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Account.Models.User;
import Chat.AppCode.ChatManagers.LobbyManager;

public class OnlineUsersManager {

	private static OnlineUsersManager ins;
	private Map<String,User> onlineUsers;

	private OnlineUsersManager() {
		onlineUsers = new HashMap<String,User>();
	}
	
	public User getUser(String sessionId){
		return onlineUsers.get(sessionId);
	}
	
	public void addUser(String sessionId,User user){
		onlineUsers.put(sessionId, user);
	}
	
	public static OnlineUsersManager instance() {
		if (ins == null) {
			ins = new OnlineUsersManager();
		}
		return ins;
	}
	
	
}
