package Chat.AppCode.ChatManagers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Account.Models.User;
import Chat.AppCode.ChatCore.ChatEndpoint;

public class LobbyManager {
	
	private List<LobbyController> lobbyControllers;
	private Map<String,User> onlineLobbyUsers;
	
	private static LobbyManager ins;

	private LobbyManager() {
		onlineLobbyUsers = new ConcurrentHashMap<String,User>();
	}
	
	public static LobbyManager instance() {
		if (ins == null) {
			ins = new LobbyManager();
		}
		return ins;
	}
	
	public void addUser(String sessionId,User user){
		onlineLobbyUsers.put(sessionId, user);
	}
	
	public User getUser(String sessionId){
		return onlineLobbyUsers.get(sessionId);
	}
}
