package Chat.Models.ADTModels;

import Chat.AppCode.ChatCore.ActionMakerFactory;
import Chat.AppCode.ChatCore.ActionMakerInterface;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Message {
	
	private ActionMakerInterface actionMaker;

    private String userFrom;
    private String groupFrom;
    
    private String groupTo;
    
    private String type;
    private String content;
    
    public String getGroupFrom(){
    	return groupFrom;
    }
    
    public void setGroupFrom(String groupFrom){
    	this.groupFrom = groupFrom;
    }
    
    public void setUserFrom(String userFrom){
    	this.userFrom = userFrom;
    }
    
    public String getUserFrom(){
    	return userFrom;
    }
    
    public void setGroupTo(String groupTo){
    	this.groupTo = groupTo;
    }
    
    public String getGroupTo(){
    	return groupTo;
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
