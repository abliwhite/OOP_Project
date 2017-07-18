package Chat.AppCode.ChatCore;

import java.io.IOException;
import java.util.Set;

import javax.websocket.EncodeException;

import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.ADTModels.Message;
import Chat.Models.DbModels.PrivacyStatusEnum;

public class ActionMakerRequest implements ActionMakerInterface {

	@Override
	public void processMessage(Message message) {
		if (LobbyManager.instance().getGroupChatById(message.getLobbyId(), message.getReceiverId())
				.getPrivacyStatusID() == 1){ //PrivacyStatusEnum.PUBLIC.ordinal() + 1) {
			Message response = new Message(message.getReceiverId(), null, message.getReceiverId(),
					message.getUserId(), message.getLobbyId(), "ResponseMessage", "Success");
			response.setActionMaker();
			response.processMessage();
			return;
		}
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

	private String getRequestContent(Message message) {
		return "User " + message.getUsername() + " wants to join this chat!";
	}

}
