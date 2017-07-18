package Chat.Models.ViewModels;

import Account.Models.User;
import Chat.Models.DbModels.InternalMessage;

public class InternalMessageViewModel {
	private InternalMessage message;
	private User user;
	
	public InternalMessageViewModel(InternalMessage message,User user){
		this.user = user;
		this.message = message;
	}
	
	public InternalMessage getMessage() {
		return message;
	}
	public void setMessage(InternalMessage message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
