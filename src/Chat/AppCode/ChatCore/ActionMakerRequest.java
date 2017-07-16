package Chat.AppCode.ChatCore;

import java.io.IOException;

import javax.websocket.EncodeException;

import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.ADTModels.Message;

public class ActionMakerRequest implements ActionMakerInterface {


	@Override
	public void processMessage(Message message){
		for (ChatEndpoint endpoint : LobbyManager.instance()) {
            synchronized (endpoint) {
                endpoint.session.getBasicRemote().sendObject(message);
            }
        }
	}

}
