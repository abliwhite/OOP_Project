package Chat.AppCode.ChatCore;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.websocket.EncodeException;

import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.ADTModels.Message;

public class ActionMakerRequest implements ActionMakerInterface {


	@Override
	public void processMessage(Message message){
		Set<ChatEndpoint> userEndpoints = LobbyManager.instance().getEndpointsByGroupId(message.getLobbyId(),message.getReceiverGroupId());
		if(userEndpoints == null) return;
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

}
