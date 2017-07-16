package Chat.AppCode.ChatCore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import Account.AppCode.AccountManager;
import Account.Models.User;
import Chat.AppCode.ChatManagers.LobbyManager;
import Chat.Models.ADTModels.Message;
import Common.AppCode.OnlineUsersManager;

//modified seminaris kodi

@SuppressWarnings("unused")
@ServerEndpoint(
        value = "/chat",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)

public class ChatEndpoint {

    private Session session;
    @SuppressWarnings("FieldCanBeLocal")
    private String username;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static Map<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        String sessionId = session.getId();
        
        User user = OnlineUsersManager.instance().getUser(sessionId);
        LobbyManager.instance().addUser(session.getId(), user);

        this.session = session;
        this.username = user.getUsername();
        
        chatEndpoints.add(this);
        users.put(session.getId(), username);

        Message message = new Message();
        message.setUserFrom(username);
        message.setContent("Connected!");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        
    	//message.setUserFrom(userFrom);
    	message.processMessage();
    	

        message.setUserFrom(users.get(session.getId()));
        sendMessageToOneUser(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setUserFrom(users.get(session.getId()));
        message.setContent("disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    	
    }

    private static void broadcast(Message message) throws IOException, EncodeException {
        for (ChatEndpoint endpoint : chatEndpoints) {
            synchronized (endpoint) {
                endpoint.session.getBasicRemote().sendObject(message);
            }
        }
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
