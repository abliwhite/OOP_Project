package Chat.AppCode.ChatCore;

import java.io.IOException;
import java.util.Set;

import javax.websocket.EncodeException;

import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.ADTModels.Message;
import Chat.Models.DbModels.InternalMessage;
import Common.AppCode.CommonConstants;

public class ActionMakerInternalMessage implements ActionMakerInterface {

	
	
	@Override
	public void processMessage(Message message) {
		Set<ChatEndpoint> userEndpoints = LobbyManager.instance().getEndpointsByGroupId(message.getLobbyId(),
				message.getReceiverId());
		LobbyManager.instance().addMessage(getInternalMessage(message), message.getLobbyId());
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
	
	private InternalMessage getInternalMessage(Message message){
		return new InternalMessage(message.getContent(),CommonConstants.getDatetime(),message.getUserId(),message.getReceiverId());
	}

	
}
