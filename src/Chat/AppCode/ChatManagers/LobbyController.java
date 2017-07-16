package Chat.AppCode.ChatManagers;

import java.util.ArrayList;
import java.util.List;

public class LobbyController {

	private List<GroupChatController> groupChatControllers;
	
	public LobbyController(){
		setGroupChatControllers(new ArrayList<GroupChatController>());
	}

	public List<GroupChatController> getGroupChatControllers() {
		return groupChatControllers;
	}

	public void setGroupChatControllers(List<GroupChatController> groupChatControllers) {
		this.groupChatControllers = groupChatControllers;
	}
	
	
	
}
