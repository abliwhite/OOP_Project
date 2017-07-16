package Chat.AppCode.DbManagers;

import java.util.List;

import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.Lobby;

public interface ChatDbManagerInterface  {

	public List<GroupChat> getAllGroupChat();
	
	public void addGroupChat(GroupChat groupChat);
	
	public Lobby getLobbyByComponentId(int componentId);
	
	public List<GroupChat> getGroupChatByUserId(int userId);
}
