package Chat.AppCode.ChatCore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import Account.AppCode.AccountManager;
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

    private static void broadcast(Message message) throws IOException, EncodeException {
        
    }

    private static void sendMessageToOneUser(Message message) throws IOException, EncodeException {
        for (ChatEndpoint endpoint : chatEndpoints) {
            synchronized (endpoint) {
                if (endpoint.session.getId().equals(getSessionId(message.getGroupTo()))) {
                    endpoint.session.getBasicRemote().sendObject(message);
                }
            }
        }
    }

    private static String getSessionId(String to) {
        if (users.containsValue(to)) {
            for (String sessionId : users.keySet()) {
                if (users.get(sessionId).equals(to)) {
                    return sessionId;
                }
            }
        }
        return null;
    }

}
