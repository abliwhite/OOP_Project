package Chat.AppCode.ChatCore;

import java.io.IOException;
import java.util.Set;

import javax.websocket.EncodeException;

import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.ADTModels.Message;

public class ActionMakerResponse implements ActionMakerInterface {

	@Override
	public void processMessage(Message message) {
		ChatEndpoint endpoint = LobbyManager.instance().getEndpointByUserId(message.getUserId());
		if (endpoint == null)
			return;
		synchronized (endpoint) {
			try {
				endpoint.getSession().getBasicRemote().sendObject(message);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}
		}
	}

}
