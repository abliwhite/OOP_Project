package Subject.Models.DbModels;

public class UserSubject {

	private Integer id;
	private int userId;
	private int subjectId;

	public UserSubject(int id, int userId, int subjectId) {
		this.id = id;
		this.userId = userId;
		this.subjectId = subjectId;
	}

	public UserSubject(int userId, int subjectId) {
		this((Integer) null, userId, subjectId);
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

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
