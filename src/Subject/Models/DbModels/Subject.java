package Subject.Models.DbModels;

import java.util.Objects;

public class Subject {

	private Integer id;
	private String name;
	private int termId;
	private int year;
	private int subjectInfoID;

	public Subject(Integer id, String name, int termId, int year, int subjectInfoID) {
		this.id = id;
		this.name = name;
		this.termId = termId;
		this.year = year;
		this.subjectInfoID = subjectInfoID;
	}

	public Subject(String name, int termId, int year, int subjectInfoID) {
		this((Integer) null, name, termId, year, subjectInfoID);
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSubjectInfoID() {
		return subjectInfoID;
	}

	public void setSubjectInfoID(int subjectInfoID) {
		this.subjectInfoID = subjectInfoID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, termId, year, subjectInfoID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Subject other = (Subject) obj;

		return other.getName().equals(name) && other.getTermId() == termId && other.getId().equals(id)
				&& other.getYear() == year && other.getSubjectInfoID() == subjectInfoID;
	}
}
