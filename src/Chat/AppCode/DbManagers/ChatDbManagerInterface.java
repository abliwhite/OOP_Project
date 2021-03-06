package Chat.AppCode.DbManagers;

import java.util.List;

import Account.Models.User;
import Chat.Models.DbModels.ExternalMessage;
import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.InternalMessage;
import Chat.Models.DbModels.Lobby;
import Chat.Models.DbModels.PrivacyStatus;

public interface ChatDbManagerInterface  {

	public List<GroupChat> getAllGroupChat();
	
	public void addGroupChat(GroupChat groupChat);
	
	public Lobby getLobbyByComponentId(int componentId);
	
	public void addLobby(Lobby lobby);
	
	public List<GroupChat> getGroupChatByUserId(int userId);

	public void deleteLobbyByComponentID(int componentId);
	
	public List<Lobby> getAllLobbies();
	
	public List<GroupChat> getAllGroupChatsByLobbyId(int lobbyId);
	
	public List<InternalMessage> getLimitInternalMessagesByGroupChatId(int groupChatId, int limit);
	
	public void addInternalMessage(InternalMessage internalMessage);
	
	public void addExternalMessage(ExternalMessage externalMessage);
	
	public List<PrivacyStatus> getAllPrivacyStatuses();
	
	public void updateGroupChat(GroupChat groupChat);

}
