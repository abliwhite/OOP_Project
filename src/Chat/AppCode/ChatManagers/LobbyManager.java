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
import Chat.AppCode.DbManagers.ChatDbManagerInterface;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.InternalMessage;
import Chat.Models.DbModels.Lobby;

public class LobbyManager {

	private List<LobbyController> lobbyControllers;
	private Map<String, User> onlineLobbyUsers;

	private ChatDbManagerInterface db;

	private static LobbyManager ins;

	private LobbyManager() {
		lobbyControllers = new ArrayList<LobbyController>();
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
		this.lobbyControllers = getLobbyControllersFromDB();
	}

	public void addUser(String sessionId, User user) {
		onlineLobbyUsers.put(sessionId, user);
	}

	public void removeUser(String sessionId) {
		User user = onlineLobbyUsers.get(sessionId);
		List<LobbyController> filteredList = lobbyControllers.stream().filter(x -> x.getOnlineUsers().contains(user))
				.collect(Collectors.toList());
		if (!filteredList.isEmpty())
			filteredList.get(0).removeUser(user);
		onlineLobbyUsers.remove(sessionId);
	}

	public void removeUserFromLobby(User user, int lobbyId) {
		List<LobbyController> filteredLobbyList = lobbyControllers.stream()
				.filter(x -> x.getOnlineUsers().contains(user)).collect(Collectors.toList());
		if (filteredLobbyList.isEmpty())
			return;
		removeUserFromGroup(user, filteredLobbyList.get(0).getGroupByUser(user).getGroupChat().getId());
		filteredLobbyList.get(0).removeUser(user);
	}

	public void removeUserFromGroup(User user, int groupId) {
		List<LobbyController> filteredLobbyList = lobbyControllers.stream()
				.filter(x -> x.getOnlineUsers().contains(user)).collect(Collectors.toList());
		if (filteredLobbyList.isEmpty())
			return;
		LobbyController lc = filteredLobbyList.get(0);
		List<GroupChatController> filteredGroupList = lc.getGroupChatControllers().stream()
				.filter(x -> x.getGroupChat().getId() == groupId).collect(Collectors.toList());
		if (filteredGroupList.isEmpty())
			return;
		GroupChatController gcc = filteredGroupList.get(0);
		gcc.removeUser(user);

	}

	public User getUser(String sessionId) {
		return onlineLobbyUsers.get(sessionId);
	}

	public void createLobby(Lobby lobby) {
		try {
			db.addLobby(lobby);
		} catch (Exception e) {
			return;
		}
		LobbyController lc = new LobbyController(lobby);
		lobbyControllers.add(lc);
	}

	public void removeLobby(Lobby lobby) {
		try {
			db.deleteLobbyByComponentID(lobby.getSubjectComponentID());
		} catch (Exception e) {
			return;
		}
		List<LobbyController> filteredList = lobbyControllers.stream().filter(x -> x.getLobby().equals(lobby))
				.collect(Collectors.toList());
		if (!filteredList.isEmpty() && filteredList != null)
			lobbyControllers.remove(filteredList.get(0));
	}

	public void createGroupChat(GroupChat groupChat) {
		try {
			db.addGroupChat(groupChat);
		} catch (Exception e) {
			return;
		}
		LobbyController lc = getLobbyControllerByLobby(groupChat.getLobbyID());
		if (lc != null)
			lc.addGroupChat(groupChat);
	}

	public void removeGroupChat(GroupChat groupChat) {
		groupChat.setActiveStatusID(1); // TODO active status Enum
		try {
			db.updateGroupChat(groupChat);
		} catch (Exception e) {
			return;
		}
		LobbyController lc = getLobbyControllerByLobby(groupChat.getLobbyID());
		if (lc != null)
			lc.removeGroupChat(groupChat);
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

	public List<InternalMessage> getMessagesByGroupId(int lobbyId, int groupId) {
		List<LobbyController> filteredList = lobbyControllers.stream().filter(x -> x.getLobby().getId() == lobbyId)
				.collect(Collectors.toList());
		if (!filteredList.isEmpty())
			return filteredList.get(0).getMessagesByGroup(groupId);
		return Collections.emptyList();
	}

	public void addMessage(InternalMessage message, int lobbyId) {
		db.addInternalMessage(message);
		List<LobbyController> filteredList = lobbyControllers.stream().filter(x -> x.getLobby().getId() == lobbyId)
				.collect(Collectors.toList());
		if (!filteredList.isEmpty())
			filteredList.get(0).addMessage(message);
	}

	// TODO sheileba akopirebs
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
