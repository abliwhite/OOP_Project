package Chat.Models.DbModels;

import java.util.Objects;

public class InternalMessage {

	private Integer id;
	private String message;
	private String dateSent;
	private int senderID;
	private int groupID;
	
	
	public InternalMessage(Integer id, String message, String dateSent, int senderID, int groupID){
		this.id = id;
		this.message = message;
		this.dateSent = dateSent;
		this.senderID = senderID;
		this.groupID = groupID;
	}
	
	public InternalMessage(String message, String dateSent, int senderID, int groupID){
		this((Integer) null, message, dateSent, senderID, groupID);
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
	
	public int getGroupID() {
		return groupID;
	}
	
	public void setgroupID(int groupID) {
		this.groupID = groupID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, message, dateSent, senderID, groupID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		InternalMessage other = (InternalMessage) obj;

		return other.getMessage().equals(message) && other.getDateSent().equals(dateSent) && other.getId().equals(id)
				&& other.getSenderID() == senderID && other.getGroupID() == groupID;
	}
	
}
