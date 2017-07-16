package Chat.AppCode.DbManagers;

import java.util.List;

import Chat.Models.DbModels.GroupChat;

public interface ChatDbManagerInterface  {

	public List<GroupChat> getAllGroupChat();
	
	public void addGroupChat(GroupChat groupChat);
}
