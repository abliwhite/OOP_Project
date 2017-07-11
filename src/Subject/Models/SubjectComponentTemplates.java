package Subject.Models;

import java.util.Objects;

public class SubjectComponentTemplates {

	private Integer id;
	private String name;
	private double markPercentage;
	private int number;

	public SubjectComponentTemplates(Integer id, String name, double markPercentage, int number) {
		this.id = id;
		this.name = name;
		this.markPercentage = markPercentage;
		this.number = number;
	}

	public SubjectComponentTemplates(String name, double markPercentage, int number) {
		this((Integer) null, name, markPercentage, number);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarkPercentage() {
		return markPercentage;
	}

	public void setMarkPercentage(double markPercentage) {
		this.markPercentage = markPercentage;
	}

	public int getNumber() {
		return number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int hashCode() {
		return Objects.hash(id, name, markPercentage, number);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		SubjectComponentTemplates other = (SubjectComponentTemplates) obj;

		return other.getId().equals(id) && other.markPercentage == markPercentage && other.name.equals(name)
				&& other.number == number;
	}
}
