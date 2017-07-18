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
	private Map<User, ChatEndpoint> userEndpoints;

	private ChatDbManagerInterface db;

	private static LobbyManager ins;

	private LobbyManager() {
		lobbyControllers = new ArrayList<LobbyController>();
		userEndpoints = new ConcurrentHashMap<User, ChatEndpoint>();
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

	public List<User> getOnlineUsers() {
		return userEndpoints.keySet().stream().collect(Collectors.toList());
	}

	public ChatEndpoint getEndpointByUserId(int userId) {
		List<User> users = getOnlineUsers().stream().filter(x -> x.getId() == userId).collect(Collectors.toList());
		if (users.isEmpty())
			return null;
		return userEndpoints.get(users.get(0));
	}

	public void addUser(User user, ChatEndpoint cep) {
		userEndpoints.put(user, cep);
	}

	public void removeUser(User user) {
		List<LobbyController> filteredList = lobbyControllers.stream().filter(x -> x.getOnlineUsers().contains(user))
				.collect(Collectors.toList());
		if (!filteredList.isEmpty())
			filteredList.get(0).removeUser(user);
		if (userEndpoints.containsKey(user))
			userEndpoints.remove(user);
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

	public void createGroupChat(User user, GroupChat groupChat) {
		GroupChatController oldgcc = getUserActiveGroupChatController(user, groupChat.getLobbyID());
		if (oldgcc != null)
			oldgcc.removeUser(user);
		try {
			db.addGroupChat(groupChat);
		} catch (Exception e) {
			return;
		}
		LobbyController lc = getLobbyControllerByLobby(groupChat.getLobbyID());
		if (lc == null)
			return;
		lc.addGroupChat(groupChat);
		lc.getGroupChatControllerById(groupChat.getId()).addUser(user, userEndpoints.get(user));
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

	public GroupChat getUserActiveGroupChat(User user, int lobbyId) {
		LobbyController lc = getLobbyControllerByLobby(lobbyId);
		if (lc == null)
			return null;
		List<GroupChatController> gccs = lc.getGroupChatControllers().stream()
				.filter(x -> x.getActiveUsers().contains(user)).collect(Collectors.toList());
		if (gccs.isEmpty())
			return null;
		return gccs.get(0).getGroupChat();
	}

	private GroupChatController getUserActiveGroupChatController(User user, int lobbyId) {
		LobbyController lc = getLobbyControllerByLobby(lobbyId);
		if (lc == null)
			return null;
		List<GroupChatController> gccs = lc.getGroupChatControllers().stream()
				.filter(x -> x.getActiveUsers().contains(user)).collect(Collectors.toList());
		if (gccs.isEmpty())
			return null;
		return gccs.get(0);
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
	
	public GroupChat getGroupChatById(int lobbyId, int groupId){
		List<LobbyController> filteredList = lobbyControllers.stream().filter(x -> x.getLobby().getId() == lobbyId)
				.collect(Collectors.toList());
		if(filteredList.isEmpty()) return null;
		return filteredList.get(0).getGroupChatControllerById(groupId).getGroupChat();
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
