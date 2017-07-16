package Chat.AppCode.ChatManagers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Account.Models.User;
import Chat.AppCode.ChatCore.ChatEndpoint;
import Chat.Models.DbModels.GroupChat;

public class LobbyManager {

	private List<LobbyController> lobbyControllers;
	private Map<String, User> onlineLobbyUsers;

	private static LobbyManager ins;

	private LobbyManager() {
		onlineLobbyUsers = new ConcurrentHashMap<String, User>();
	}

	public static LobbyManager instance() {
		if (ins == null) {
			ins = new LobbyManager();
		}
		return ins;
	}

	public void addUser(String sessionId, User user) {
		onlineLobbyUsers.put(sessionId, user);
	}
	
	public void removeUser(String sessionId){
		onlineLobbyUsers.remove(sessionId);
	}

	public User getUser(String sessionId) {
		return onlineLobbyUsers.get(sessionId);
	}

	public List<GroupChat> getActiveGroupChats(int subjectComponentID){
		return getLobbyController(subjectComponentID).getActiveGroupChats();
	}
	
	private LobbyController getLobbyController(int subjectComponentID) {
		return lobbyControllers.stream().filter(x -> x.getLobby().getSubjectComponentID() == subjectComponentID)
				.collect(Collectors.toList()).get(0);
	}
}
