package Chat.Models.DbModels;

import java.util.Objects;

public class GroupChat {

	private Integer id;
	private String name;
	private String createDate;
	private int lobbyID;
	private int creatorID;
	private int privacyStatusID;
	private int activeStatusID;

	public GroupChat(Integer id, String name, String createDate, int lobbyID, int creatorID, int privacyStatusID,
			int activeStatusID) {
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.lobbyID = lobbyID;
		this.creatorID = creatorID;
		this.privacyStatusID = privacyStatusID;
		this.activeStatusID = activeStatusID;
	}

	public GroupChat(String name, String createDate, int lobbyID, int creatorID, int privacyStatusID,
			int activeStatusID) {
		this((Integer) null, name, createDate, lobbyID, creatorID, privacyStatusID, activeStatusID);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getLobbyID() {
		return lobbyID;
	}

	public void setLobbyID(int lobbyID) {
		this.lobbyID = lobbyID;
	}

	public int getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(int creatorID) {
		this.creatorID = creatorID;
	}

	public int getPrivacyStatusID() {
		return privacyStatusID;
	}

	public void setPrivacyStatusID(int privacyStatusID) {
		this.privacyStatusID = privacyStatusID;
	}

	public int getActiveStatusID() {
		return activeStatusID;
	}

	public void setActiveStatusID(int activeStatusID) {
		this.activeStatusID = activeStatusID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, createDate, lobbyID, creatorID, privacyStatusID, activeStatusID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		GroupChat other = (GroupChat) obj;

		return other.getId().equals(id) && other.getName().equals(name) && other.getCreateDate().equals(createDate)
				&& other.getActiveStatusID() == activeStatusID && other.getPrivacyStatusID() == privacyStatusID
				&& other.getLobbyID() == lobbyID && other.getCreatorID() == creatorID;
	}

}
