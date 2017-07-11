package Subject.Models;

public class UserSubject {

	private int id, userId, subjectId;
	
	public UserSubject(int id, int userId, int subjectId) {
		this.id = id;
		this.userId = userId;
		this.subjectId = subjectId;
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
