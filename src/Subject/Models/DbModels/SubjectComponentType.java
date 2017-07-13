package Subject.Models.DbModels;

import java.util.Objects;

public class SubjectComponentType {

	private Integer id;
	private String name;

	public SubjectComponentType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public SubjectComponentType(String name) {
		this((Integer) null, name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int hashCode() {
		return Objects.hash(id, name);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		SubjectComponentType other = (SubjectComponentType) obj;

		return other.getId().equals(id) && other.name.equals(name);
	}
}
