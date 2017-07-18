package Chat.Models.DbModels;

import java.util.Objects;

public class ExternalMessage {

	private Integer id;
	private String message;
	private String dateSent;
	private int senderID;
	private int senderGroupID;
	private int receiverGroupID;
	
	
	public ExternalMessage(Integer id, String message, String dateSent, int senderID, int senderGroupID, int receiverGroupID){
		this.id = id;
		this.message = message;
		this.dateSent = dateSent;
		this.senderID = senderID;
		this.senderGroupID = senderGroupID;
		this.receiverGroupID = receiverGroupID;
	}
	
	public ExternalMessage(String message, String dateSent, int senderID, int senderGroupID, int receiverGroupID){
		this((Integer) null, message, dateSent, senderID, senderGroupID, receiverGroupID);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDateSent() {
		return dateSent;
	}
	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}
	
	public int getSenderID() {
		return senderID;
	}
	
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
	
	public int getSenderGroupID() {
		return senderGroupID;
	}
	
	public void setSenderGroupID(int senderGroupID) {
		this.senderGroupID = senderGroupID;
	}
	
	public int getReceiverGroupID() {
		return receiverGroupID;
	}
	
	public void setReceiverGroupID(int receiverGroupID) {
		this.receiverGroupID = receiverGroupID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, message, dateSent, senderID, senderGroupID, receiverGroupID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		ExternalMessage other = (ExternalMessage) obj;

		return other.getMessage().equals(message) && other.getDateSent().equals(dateSent) && other.getId().equals(id)
				&& other.getSenderID() == senderID && other.getSenderGroupID() == senderGroupID && other.getReceiverGroupID() == receiverGroupID;
	}
	
}
