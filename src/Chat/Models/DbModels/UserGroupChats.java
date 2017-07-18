package Chat.Models.DbModels;

public class UserGroupChats {

	private Integer id;
	private int userId;
	private int groupChatId;

	public UserGroupChats(int id, int userId, int groupChatId) {
		this.id = id;
		this.userId = userId;
		this.groupChatId = groupChatId;
	}

	@SuppressWarnings("null")
	public UserGroupChats(int userId, int groupChatId) {
		this((Integer) null, userId, groupChatId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupChatId() {
		return groupChatId;
	}

	public void setGroupChatId(int groupChatId) {
		this.groupChatId = groupChatId;
	}
}
