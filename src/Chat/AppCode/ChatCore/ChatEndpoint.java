package Chat.AppCode.ChatCore;

import java.io.IOException;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import Account.Models.User;
import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.ADTModels.Message;
import Common.AppCode.OnlineUsersManager;

//modified seminaris kodi


@ServerEndpoint(
        value = "/chat/{id}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)

public class ChatEndpoint {

    private Session session;

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        String sessionId = session.getId();
        
        User user = OnlineUsersManager.instance().getUser(sessionId);
        LobbyManager.instance().addUser(session.getId(), user);

        this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
    	
    	message.processMessage();
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        LobbyManager.instance().removeUser(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    	
    }

    public Session getSession(){
    	return session;
    }
    

}
