package Chat.AppCode.ChatManagers;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import Account.Models.User;
import Chat.AppCode.ChatCore.ChatEndpoint;
import Chat.AppCode.DbManagers.ChatDbManagerInterface;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.Lobby;

public class LobbyManager {

	private List<LobbyController> lobbyControllers;
	private Map<String, User> onlineLobbyUsers;

	private ChatDbManagerInterface db;

	private static LobbyManager ins;

	private LobbyManager() {
		lobbyControllers = getLobbyControllersFromDB();
		onlineLobbyUsers = new ConcurrentHashMap<String, User>();
	}

	private List<LobbyController> getLobbyControllersFromDB() {
		List<Lobby> lobbies = db.getAllLobbies();
		List<LobbyController> lcs = lobbies.stream().map(x -> new LobbyController(x)).collect(Collectors.toList());
		lcs.stream().forEach(x -> x.setGroupChatControllers(getAllGroupChatsByLobby(x.getLobby())));
		return lcs;
	}

	private List<GroupChatController> getAllGroupChatsByLobby(Lobby lobby) {
		List<GroupChat> groupChats = db.getAllGroupChatsByLobbyId(lobby.getId());
		List<GroupChatController> gccs = groupChats.stream().map(x -> new GroupChatController(x))
				.collect(Collectors.toList());
		gccs.forEach(x -> x.setMessages(db.getLimitInternalMessagesByGroupChatId(x.getGroupChat().getId(), 100)));
		return gccs;
	}

	public static LobbyManager instance() {
		if (ins == null) {
			ins = new LobbyManager();
		}
		return ins;
	}

	public void initialize(ChatDbManagerInterface db) {
		this.db = db;
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

	public Set<User> getUsersByGroupId(int lobbyId, int groupId) {
		LobbyController lobbyController = getLobbyControllerByLobby(lobbyId);
		GroupChatController gcc = lobbyController.getGroupChatControllerById(groupId);
		if (gcc != null) {
			return gcc.getUsers();
		}
		return Collections.emptySet();
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
