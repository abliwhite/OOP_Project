package Chat.Models.DbModels;

import java.util.Objects;

public class Lobby {
	
	private Integer id;
	private int subjectComponentID;
	

	public Lobby(){
		
	}
	
	public Lobby(Integer id, int subjectComponentID) {
		this.id = id;
		this.subjectComponentID = subjectComponentID;
	}

	public Lobby(int subjectComponentID) {
		this((Integer) null, subjectComponentID);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSubjectComponentID() {
		return subjectComponentID;
	}

	public void setSubjectComponentID(int subjectComponentID) {
		this.subjectComponentID = subjectComponentID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, subjectComponentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Lobby other = (Lobby) obj;

		return other.getId().equals(id) && other.getSubjectComponentID() == subjectComponentID;
	}
}
