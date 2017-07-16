package Chat.AppCode.ChatCore;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Chat.Models.ADTModels.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.logging.Logger;

//modified seminaris kodi

public class MessageDecoder implements Decoder.Text<Message> {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public Message decode(String s) throws DecodeException {
        log.info("Incoming message : " + s);

        Gson gson = new Gson();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // do nothing
    }

    @Override
    public void destroy() {
        // do nothing
    }

}
