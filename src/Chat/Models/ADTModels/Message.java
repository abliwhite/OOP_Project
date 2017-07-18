package Chat.Models.ADTModels;

import Chat.AppCode.ChatCore.ActionMakerFactory;
import Chat.AppCode.ChatCore.ActionMakerInterface;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Message {
	
	private ActionMakerInterface actionMaker;

    private int userId;
    private String username;
    private int senderGroupId;
    
    private int receiverId;
    private int lobbyId;
    
    private String type;
    private String content;
    
    public String getUsername(){
    	return username;
    }
    
    public void setUsername(String username){
    	this.username = username;
    }
    
    public int getSenderGroupId(){
    	return senderGroupId;
    }
    
    public void setSenderGroupId(int groupFrom){
    	this.senderGroupId = groupFrom;
    }
    
    public void setUserId(int userFrom){
    	this.userId = userFrom;
    }
    
    public int getUserId(){
    	return userId;
    }
    
    public void setReceiverId(int groupTo){
    	this.receiverId = groupTo;
    }
    
    public int getReceiverId(){
    	return receiverId;
    }
    
    public int getLobbyId(){
    	return lobbyId;
    }
    
    public void setLobbyId(int lobbyId){
    	this.lobbyId = lobbyId;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setActionMaker(){
    	actionMaker = ActionMakerFactory.getActionMaker(type);
    }
    
    public void processMessage(){
    	actionMaker.processMessage(this);
    }
}
