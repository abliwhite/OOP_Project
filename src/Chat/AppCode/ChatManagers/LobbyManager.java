package Chat.AppCode.ChatManagers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import Account.Models.User;
import Chat.AppCode.ChatCore.ChatEndpoint;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.Lobby;

public class LobbyManager {

	private List<LobbyController> lobbyControllers;
	private Map<String, User> onlineLobbyUsers;

	private static LobbyManager ins;

	private LobbyManager() {
		lobbyControllers = new ArrayList<LobbyController>();
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

	public void removeUser(String sessionId) {
		onlineLobbyUsers.remove(sessionId);
	}

	public User getUser(String sessionId) {
		return onlineLobbyUsers.get(sessionId);
	}

	public void createLobby(Lobby lobby) {
		LobbyController lc = new LobbyController(lobby);
		lobbyControllers.add(lc);
	}

	public void removeLobby(Lobby lobby) {
		List<LobbyController> filteredList = lobbyControllers.stream().filter(x -> x.getLobby().equals(lobby))
				.collect(Collectors.toList());
		if (!filteredList.isEmpty() && filteredList != null)
			lobbyControllers.remove(filteredList.get(0));
	}

	public void createGroupChat(Lobby lobby, GroupChat groupChat) {
		LobbyController lc = getLobbyControllerByLobby(lobby.getId());
		if (lc != null)
			lc.addGroupChat(groupChat);
	}

	public void removeGroupChat(Lobby lobby, GroupChat groupChat) {
		LobbyController lc = getLobbyControllerByLobby(lobby.getId());
		if (lc != null)
			lc.addGroupChat(groupChat);
	}

	public List<GroupChat> getActiveGroupChats(int subjectComponentID) {
		LobbyController lc = getLobbyControllerByComponent(subjectComponentID);
		if (lc == null)
			return Collections.emptyList();
		return lc.getActiveGroupChats();
	}

	public Set<ChatEndpoint> getEndpointsByGroupId(int lobbyId, int receiverGroupId) {
		LobbyController lobbyController = getLobbyControllerByLobby(lobbyId);
		GroupChatController gcc = lobbyController.getGroupChatControllerById(receiverGroupId);
		if (gcc != null) {
			return gcc.getUserEndpoints();
		}
		return Collections.emptySet();
	}

	public List<User> getUsersByGroupId(int lobbyId, int groupId) {
		LobbyController lobbyController = getLobbyControllerByLobby(lobbyId);
		GroupChatController gcc = lobbyController.getGroupChatControllerById(groupId);
		if (gcc != null) {
			return gcc.getUsers();
		}
		return Collections.emptyList();
	}

	private LobbyController getLobbyControllerByComponent(int subjectComponentID) {
		List<LobbyController> filteredList = lobbyControllers.stream()
				.filter(x -> x.getLobby().getSubjectComponentID() == subjectComponentID).collect(Collectors.toList());
		if (filteredList.isEmpty())
			return null;
		return filteredList.get(0);
	}

	private LobbyController getLobbyControllerByLobby(int lobbyID) {
		List<LobbyController> filteredList = lobbyControllers.stream().filter(x -> x.getLobby().getId() == lobbyID)
				.collect(Collectors.toList());
		if (filteredList.isEmpty())
			return null;
		return filteredList.get(0);
	}

}
