package Chat.AppCode.ChatCore;

import java.io.IOException;
import java.util.Set;

import javax.websocket.EncodeException;

import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.ADTModels.Message;

public class ActionMakerRequest implements ActionMakerInterface {
	
	
	
	@Override
	public void processMessage(Message message) {
		message.setContent(getRequestContent(message));
		Set<ChatEndpoint> userEndpoints = LobbyManager.instance().getEndpointsByGroupId(message.getLobbyId(),
				message.getReceiverId());
		if (userEndpoints == null)
			return;
		for (ChatEndpoint endpoint : userEndpoints) {
			synchronized (endpoint) {
				try {
					endpoint.getSession().getBasicRemote().sendObject(message);
				} catch (IOException | EncodeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private String getRequestContent(Message message){
		return "User " + message.getUsername() + " wants to join this chat!";
	}

}
