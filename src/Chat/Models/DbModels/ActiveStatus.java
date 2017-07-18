package Chat.Models.DbModels;

import java.util.Objects;

public class ActiveStatus {

	private Integer id;
	private String name;
	

	public ActiveStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public ActiveStatus(String name) {
		this((Integer) null, name);
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

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		ActiveStatus other = (ActiveStatus) obj;

		return other.getId().equals(id) && other.getName().equals(name);
	}
}
